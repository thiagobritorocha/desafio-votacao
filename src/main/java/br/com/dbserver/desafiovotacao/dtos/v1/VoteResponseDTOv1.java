package br.com.dbserver.desafiovotacao.dtos.v1;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class VoteResponseDTOv1 {
  private UUID id;
  private String cpf;
  private String vote;
  private UUID votingSessionId;
}
