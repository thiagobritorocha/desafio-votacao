package br.com.dbserver.desafiovotacao.services.v1;

import br.com.dbserver.desafiovotacao.converters.VoteConverter;
import br.com.dbserver.desafiovotacao.dtos.v1.VoteRequestDTOv1;
import br.com.dbserver.desafiovotacao.dtos.v1.VoteResponseDTOv1;
import br.com.dbserver.desafiovotacao.enums.AssociateStatus;
import br.com.dbserver.desafiovotacao.exceptions.VoteNotAuthorizedException;
import br.com.dbserver.desafiovotacao.exceptions.VoteNotFoundException;
import br.com.dbserver.desafiovotacao.exceptions.VotingSessionExpiredException;
import br.com.dbserver.desafiovotacao.models.Vote;
import br.com.dbserver.desafiovotacao.repositories.VoteRepository;
import br.com.dbserver.desafiovotacao.user.AssociateClient;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteService {
  private final VoteRepository repository;
  private final VoteConverter converter;
  private final VotingSessionService sessionService;
  private final AssociateClient associateClient;

  public List<VoteResponseDTOv1> getAllVotes() {
    return converter.toResponseDTOv1(repository.findAll());
  }

  public VoteResponseDTOv1 getVote(UUID id) throws VoteNotFoundException {
    Optional<Vote> vote = repository.findById(id);
    return converter.toResponseDTOv1(vote.orElseThrow(() -> new VoteNotFoundException(id)));
  }

  @SneakyThrows
  public VoteResponseDTOv1 addVote(VoteRequestDTOv1 request) {
    validateVote(request);
    var vote = repository.saveAndFlush(converter.toModel(request));
    return converter.toResponseDTOv1(vote);
  }

  @SneakyThrows
  private void validateVote(VoteRequestDTOv1 request) {
    var session = sessionService.getVotingSession(request.getVotingSessionId());

    if (sessionService.isExpired(session)) {
      throw new VotingSessionExpiredException(session);
    }
    if (repository.findFirstByVotingSessionAndCpf(session, request.getCpf()) != null) {
      throw new VoteNotAuthorizedException(
          "This CPF has already voted in this session: " + request.getCpf());
    }
    var response = associateClient.getAssociateStatusByCpf(request.getCpf()).getBody();
    if (Objects.isNull(response)) {
      throw new VoteNotAuthorizedException(
          "Unable to retrieve associate status for CPF: " + request.getCpf());
    }
    if (AssociateStatus.UNABLE_TO_VOTE.equals(response.getStatus())) {
      throw new VoteNotAuthorizedException(
          "This CPF is not able to vote in this session: " + request.getCpf());
    }
  }
}
