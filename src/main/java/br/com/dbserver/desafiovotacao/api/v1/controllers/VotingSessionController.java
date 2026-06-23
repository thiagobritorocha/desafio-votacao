package br.com.dbserver.desafiovotacao.api.v1.controllers;

import br.com.dbserver.desafiovotacao.dtos.v1.VotingSessionReportDTOv1;
import br.com.dbserver.desafiovotacao.dtos.v1.VotingSessionRequestDTOv1;
import br.com.dbserver.desafiovotacao.dtos.v1.VotingSessionResponseDTOv1;
import br.com.dbserver.desafiovotacao.exceptions.VotingSessionNotFoundException;
import br.com.dbserver.desafiovotacao.exceptions.VotingSessionNotValidException;
import br.com.dbserver.desafiovotacao.services.v1.VotingSessionService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/voting-sessions")
public class VotingSessionController {
  private final VotingSessionService service;

  @GetMapping
  public ResponseEntity<List<VotingSessionResponseDTOv1>> list() {
    List<VotingSessionResponseDTOv1> sessions = service.getAllSessions();
    return new ResponseEntity<>(sessions, HttpStatus.OK);
  }

  @GetMapping("{id}")
  public ResponseEntity<VotingSessionResponseDTOv1> get(@PathVariable UUID id)
      throws VotingSessionNotFoundException {
    VotingSessionResponseDTOv1 session = service.getSession(id);
    return new ResponseEntity<>(session, HttpStatus.OK);
  }

  @GetMapping("{id}/report")
  public ResponseEntity<VotingSessionReportDTOv1> report(@PathVariable UUID id)
      throws VotingSessionNotFoundException {
    VotingSessionReportDTOv1 report = service.generateReport(id);
    return new ResponseEntity<>(report, HttpStatus.OK);
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public ResponseEntity<VotingSessionResponseDTOv1> create(
      @Valid @RequestBody final VotingSessionRequestDTOv1 request)
      throws VotingSessionNotValidException {
    VotingSessionResponseDTOv1 response = service.openSession(request);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }
}
