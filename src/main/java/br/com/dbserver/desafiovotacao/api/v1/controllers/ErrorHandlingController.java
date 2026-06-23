package br.com.dbserver.desafiovotacao.api.v1.controllers;

import br.com.dbserver.desafiovotacao.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ErrorHandlingController extends ResponseEntityExceptionHandler {
  private ResponseEntity<ErrorMessage> error(
      String message, HttpStatus httpStatus, Exception exception, HttpServletRequest request) {
    ErrorMessage error =
        ErrorMessage.builder()
            .message(message)
            .status(httpStatus.value())
            .timestamp(LocalDateTime.now())
            .uri(request.getRequestURI())
            .detail(exception.getLocalizedMessage())
            .build();
    return new ResponseEntity<>(error, httpStatus);
  }

  @ExceptionHandler(TopicNotFoundException.class)
  public ResponseEntity<ErrorMessage> handleTopicNotFoundException(
      TopicNotFoundException exception, HttpServletRequest request) {
    log.error("Topic Not Found: {}", exception.getMessage(), exception);
    return error("Topic Not Found", HttpStatus.NOT_FOUND, exception, request);
  }

  @ExceptionHandler(TopicNotValidException.class)
  public ResponseEntity<ErrorMessage> handleTopicNotValidException(
      TopicNotValidException exception, HttpServletRequest request) {
    log.error("Invalid Topic: {}", exception.getMessage(), exception);
    return error("Invalid Topic", HttpStatus.BAD_REQUEST, exception, request);
  }

  @ExceptionHandler(VoteNotAuthorizedException.class)
  public ResponseEntity<ErrorMessage> handleVoteNotAuthorizedException(
      VoteNotAuthorizedException exception, HttpServletRequest request) {
    log.error("Unauthorized Vote: {}", exception.getMessage(), exception);
    return error("Unauthorized Vote", HttpStatus.BAD_REQUEST, exception, request);
  }

  @ExceptionHandler(VoteNotFoundException.class)
  public ResponseEntity<ErrorMessage> handleVoteNotFoundException(
      VoteNotFoundException exception, HttpServletRequest request) {
    log.error("Vote Not Found: {}", exception.getMessage(), exception);
    return error("Vote Not Found", HttpStatus.NOT_FOUND, exception, request);
  }

  @ExceptionHandler(VoteNotValidException.class)
  public ResponseEntity<ErrorMessage> handleVoteNotValidException(
      VoteNotValidException exception, HttpServletRequest request) {
    log.error("Invalid Vote: {}", exception.getMessage(), exception);
    return error("Invalid Vote", HttpStatus.BAD_REQUEST, exception, request);
  }

  @ExceptionHandler(VotingSessionExpiredException.class)
  public ResponseEntity<ErrorMessage> handleVotingSessionExpiredException(
      VotingSessionExpiredException exception, HttpServletRequest request) {
    log.error("Voting Session Expired: {}", exception.getMessage(), exception);
    return error("Voting Session Expired", HttpStatus.BAD_REQUEST, exception, request);
  }

  @ExceptionHandler(VotingSessionNotFoundException.class)
  public ResponseEntity<ErrorMessage> handleVotingSessionNotFoundException(
      VotingSessionNotFoundException exception, HttpServletRequest request) {
    log.error("Voting Session Not Found: {}", exception.getMessage(), exception);
    return error("Voting Session Not Found", HttpStatus.NOT_FOUND, exception, request);
  }

  @ExceptionHandler(VotingSessionNotValidException.class)
  public ResponseEntity<ErrorMessage> handleVotingSessionNotValidException(
      VotingSessionNotValidException exception, HttpServletRequest request) {
    log.error("Invalid Voting Session: {}", exception.getMessage(), exception);
    return error("Invalid Voting Session", HttpStatus.BAD_REQUEST, exception, request);
  }
}
