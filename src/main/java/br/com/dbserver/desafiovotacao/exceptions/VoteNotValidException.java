package br.com.dbserver.desafiovotacao.exceptions;

public class VoteNotValidException extends Exception {
  public VoteNotValidException(String message) {
    super(message);
  }
}
