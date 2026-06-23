package br.com.dbserver.desafiovotacao.user;

import br.com.dbserver.desafiovotacao.user.response.AssociateResponse;
import org.springframework.http.ResponseEntity;

public interface AssociateClient {

  ResponseEntity<AssociateResponse> getAssociateStatusByCpf(String cpf);
}
