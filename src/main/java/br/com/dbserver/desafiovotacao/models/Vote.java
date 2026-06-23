package br.com.dbserver.desafiovotacao.models;

import br.com.dbserver.desafiovotacao.enums.VoteOption;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "votes")
@Table(
    uniqueConstraints =
        @UniqueConstraint(
            columnNames = {"cpf", "voting_session_id"},
            name = "per_session_unique"))
public class Vote {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID voteId;

  @NotNull
  @Column(name = "cpf")
  private String cpf;

  @NotNull
  @Enumerated(EnumType.STRING)
  private VoteOption vote;

  @NotNull
  @ManyToOne()
  @JoinColumn(name = "voting_session_id")
  private VotingSession votingSession;
}
