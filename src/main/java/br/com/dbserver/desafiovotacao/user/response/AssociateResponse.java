package br.com.dbserver.desafiovotacao.user.response;

import br.com.dbserver.desafiovotacao.enums.AssociateStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AssociateResponse {
  private AssociateStatus status;
}
