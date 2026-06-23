package br.com.dbserver.desafiovotacao.user;

import br.com.dbserver.desafiovotacao.enums.AssociateStatus;
import br.com.dbserver.desafiovotacao.user.response.AssociateResponse;
import java.util.Random;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AssociateClientImpl implements AssociateClient {

  private static final Random random = new Random();

  public ResponseEntity<AssociateResponse> getAssociateStatusByCpf(String cpf) {
    AssociateStatus randomStatus =
        AssociateStatus.values()[random.nextInt(AssociateStatus.values().length)];

    AssociateResponse response = new AssociateResponse();
    response.setStatus(randomStatus);

    return ResponseEntity.ok(response);
  }
}
