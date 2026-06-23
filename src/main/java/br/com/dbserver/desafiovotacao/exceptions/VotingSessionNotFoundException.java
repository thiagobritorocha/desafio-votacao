package br.com.dbserver.desafiovotacao.exceptions;

import java.util.UUID;

public class VotingSessionNotFoundException extends Exception {
  public VotingSessionNotFoundException(UUID id) {
    super("Invalid Voting Session ID: " + id.toString());
  }
}
