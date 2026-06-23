package br.com.dbserver.desafiovotacao.dtos.v1;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VotingSessionRequestDTOv1 {
  @NotNull private UUID topicId;
  private LocalDateTime expires;
}
