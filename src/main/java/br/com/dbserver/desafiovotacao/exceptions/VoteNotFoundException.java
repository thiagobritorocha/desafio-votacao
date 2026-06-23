package br.com.dbserver.desafiovotacao.exceptions;

import java.util.UUID;

public class VoteNotFoundException extends Exception {
  public VoteNotFoundException(UUID id) {
    super("Invalid Vote ID: " + id.toString());
  }
}
