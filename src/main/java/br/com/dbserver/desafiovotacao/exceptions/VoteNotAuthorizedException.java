package br.com.dbserver.desafiovotacao.exceptions;

public class VoteNotAuthorizedException extends Exception {
  public VoteNotAuthorizedException(String message) {
    super(message);
  }
}
