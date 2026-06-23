package br.com.dbserver.desafiovotacao.repositories;

import br.com.dbserver.desafiovotacao.enums.VoteOption;
import br.com.dbserver.desafiovotacao.models.Vote;
import br.com.dbserver.desafiovotacao.models.VotingSession;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, UUID> {
  UUID countVotesByVotingSessionAndVote(VotingSession session, VoteOption vote);

  Vote findFirstByVotingSessionAndCpf(VotingSession session, String cpf);
}
