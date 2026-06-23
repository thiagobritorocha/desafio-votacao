package br.com.dbserver.desafiovotacao.exceptions;

import java.util.UUID;

public class TopicNotFoundException extends Exception {
  public TopicNotFoundException(UUID id) {
    super("Invalid Topic ID: " + id.toString());
  }
}
