package br.com.dbserver.desafiovotacao.dtos.v1;

import br.com.dbserver.desafiovotacao.enums.VoteOption;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VoteRequestDTOv1 {
  @NotNull
  @Pattern(regexp = "^\\d{9}-\\d{2}$", message = "must be in format 000000000-00")
  private String cpf;

  @NotNull private VoteOption vote;

  @NotNull private UUID votingSessionId;
}
