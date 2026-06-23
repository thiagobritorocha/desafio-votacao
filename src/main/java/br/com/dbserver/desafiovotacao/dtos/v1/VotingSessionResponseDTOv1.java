package br.com.dbserver.desafiovotacao.dtos.v1;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VotingSessionResponseDTOv1 {
  private UUID id;
  private TopicResponseDTOv1 topic;
  private LocalDateTime expires;
}
