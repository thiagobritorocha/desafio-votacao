package br.com.dbserver.desafiovotacao.converters;

import br.com.dbserver.desafiovotacao.dtos.v1.VoteRequestDTOv1;
import br.com.dbserver.desafiovotacao.dtos.v1.VoteResponseDTOv1;
import br.com.dbserver.desafiovotacao.models.Vote;
import br.com.dbserver.desafiovotacao.models.VotingSession;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class VoteConverter {
  public Vote toModel(VoteRequestDTOv1 dto) {
    Vote model = new Vote();
    model.setCpf(dto.getCpf());
    model.setVote(dto.getVote());

    VotingSession session = new VotingSession();
    session.setVotingSessionId(dto.getVotingSessionId());
    model.setVotingSession(session);

    return model;
  }

  public VoteResponseDTOv1 toResponseDTOv1(Vote model) {
    return VoteResponseDTOv1.builder()
        .id(model.getVoteId())
        .cpf(model.getCpf())
        .vote(model.getVote().name())
        .votingSessionId(model.getVotingSession().getVotingSessionId())
        .build();
  }

  public List<VoteResponseDTOv1> toResponseDTOv1(List<Vote> model) {
    List<VoteResponseDTOv1> dto = new ArrayList<>();
    model.forEach(
        vote -> {
          dto.add(toResponseDTOv1(vote));
        });
    return dto;
  }
}
