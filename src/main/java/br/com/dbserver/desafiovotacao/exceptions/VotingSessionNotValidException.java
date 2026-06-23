package br.com.dbserver.desafiovotacao.exceptions;

public class VotingSessionNotValidException extends Exception {
  public VotingSessionNotValidException(String message) {
    super(message);
  }
}
