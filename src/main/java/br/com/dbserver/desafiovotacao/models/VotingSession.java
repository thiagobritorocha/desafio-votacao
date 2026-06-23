package br.com.dbserver.desafiovotacao.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "voting_sessions")
public class VotingSession {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID votingSessionId;

  @NotNull
  @OneToOne
  @JoinColumn(name = "topic_id")
  private Topic topic;

  @NotNull private LocalDateTime expires;

  @OneToMany(mappedBy = "votingSession")
  private List<Vote> votes;
}
