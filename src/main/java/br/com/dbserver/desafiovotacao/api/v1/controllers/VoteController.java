package br.com.dbserver.desafiovotacao.api.v1.controllers;

import br.com.dbserver.desafiovotacao.dtos.v1.VoteRequestDTOv1;
import br.com.dbserver.desafiovotacao.dtos.v1.VoteResponseDTOv1;
import br.com.dbserver.desafiovotacao.exceptions.VoteNotFoundException;
import br.com.dbserver.desafiovotacao.services.v1.VoteService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/votes")
public class VoteController {
  private final VoteService service;

  @GetMapping
  public ResponseEntity<List<VoteResponseDTOv1>> list() {
    List<VoteResponseDTOv1> votes = service.getAllVotes();
    return new ResponseEntity<>(votes, HttpStatus.OK);
  }

  @GetMapping("{id}")
  public ResponseEntity<VoteResponseDTOv1> get(@PathVariable UUID id) throws VoteNotFoundException {
    VoteResponseDTOv1 vote = service.getVote(id);
    return new ResponseEntity<>(vote, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<VoteResponseDTOv1> create(
      @Valid @RequestBody final VoteRequestDTOv1 request) {
    VoteResponseDTOv1 response = service.addVote(request);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }
}
