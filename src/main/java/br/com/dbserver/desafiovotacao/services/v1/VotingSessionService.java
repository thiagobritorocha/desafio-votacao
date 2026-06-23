package br.com.dbserver.desafiovotacao.services.v1;

import br.com.dbserver.desafiovotacao.converters.TopicConverter;
import br.com.dbserver.desafiovotacao.converters.VoteConverter;
import br.com.dbserver.desafiovotacao.converters.VotingSessionConverter;
import br.com.dbserver.desafiovotacao.dtos.v1.VotingSessionReportDTOv1;
import br.com.dbserver.desafiovotacao.dtos.v1.VotingSessionRequestDTOv1;
import br.com.dbserver.desafiovotacao.dtos.v1.VotingSessionResponseDTOv1;
import br.com.dbserver.desafiovotacao.enums.VoteOption;
import br.com.dbserver.desafiovotacao.enums.VoteResult;
import br.com.dbserver.desafiovotacao.exceptions.TopicNotFoundException;
import br.com.dbserver.desafiovotacao.exceptions.VotingSessionNotFoundException;
import br.com.dbserver.desafiovotacao.exceptions.VotingSessionNotValidException;
import br.com.dbserver.desafiovotacao.models.VotingSession;
import br.com.dbserver.desafiovotacao.repositories.VotingSessionRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VotingSessionService {
  private final VotingSessionRepository repository;
  private final VotingSessionConverter sessionConverter;
  private final TopicConverter topicConverter;
  private final TopicService topicService;
  private final VoteConverter voteConverter;

  public List<VotingSessionResponseDTOv1> getAllSessions() {
    return sessionConverter.toResponseDTOv1(repository.findAll());
  }

  public VotingSessionResponseDTOv1 getSession(UUID id) throws VotingSessionNotFoundException {
    VotingSession session = getVotingSession(id);
    return sessionConverter.toResponseDTOv1(session);
  }

  public VotingSession getVotingSession(UUID id) throws VotingSessionNotFoundException {
    Optional<VotingSession> session = repository.findById(id);
    return session.orElseThrow(() -> new VotingSessionNotFoundException(id));
  }

  public VotingSessionReportDTOv1 generateReport(UUID id) throws VotingSessionNotFoundException {
    VotingSession session = getVotingSession(id);
    Long yesVotes = getYesVotes(session);
    Long noVotes = getNoVotes(session);
    return VotingSessionReportDTOv1.builder()
        .id(session.getVotingSessionId())
        .topic(topicConverter.toResponseDTOv1(session.getTopic()))
        .votes(voteConverter.toResponseDTOv1(session.getVotes()))
        .expired(isExpired(session))
        .yes(yesVotes)
        .no(noVotes)
        .result(getVoteResult(yesVotes, noVotes))
        .build();
  }

  private Long getYesVotes(VotingSession session) {
    return session.getVotes().stream()
        .filter(vote -> vote.getVote().equals(VoteOption.YES))
        .count();
  }

  private Long getNoVotes(VotingSession session) {
    return session.getVotes().stream()
        .filter(vote -> vote.getVote().equals(VoteOption.NO))
        .count();
  }

  private VoteResult getVoteResult(Long yes, Long no) {
    if (yes > no) {
      return VoteResult.APPROVED;
    } else if (no > yes) {
      return VoteResult.REJECTED;
    } else {
      return VoteResult.TIED;
    }
  }

  public VotingSessionResponseDTOv1 openSession(VotingSessionRequestDTOv1 request)
      throws VotingSessionNotValidException {
    validateTopic(request);
    setDefaultExpires(request);

    VotingSession session;
    try {
      session = repository.saveAndFlush(sessionConverter.toModel(request));
    } catch (Exception ex) {
      throw new VotingSessionNotValidException(request.getTopicId().toString());
    }
    return sessionConverter.toResponseDTOv1(session);
  }

  public boolean isExpired(VotingSession session) {
    return session.getExpires().isBefore(LocalDateTime.now());
  }

  private void setDefaultExpires(VotingSessionRequestDTOv1 request) {
    if (request.getExpires() == null) {
      request.setExpires(LocalDateTime.now().plusMinutes(1));
    }
  }

  private void validateTopic(VotingSessionRequestDTOv1 request)
      throws VotingSessionNotValidException {
    try {
      topicService.getTopic(request.getTopicId());
    } catch (TopicNotFoundException ex) {
      throw new VotingSessionNotValidException(request.getTopicId().toString());
    }
  }
}
