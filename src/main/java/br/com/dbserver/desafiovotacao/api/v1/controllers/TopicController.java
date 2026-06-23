package br.com.dbserver.desafiovotacao.api.v1.controllers;

import br.com.dbserver.desafiovotacao.dtos.v1.TopicRequestDTOv1;
import br.com.dbserver.desafiovotacao.dtos.v1.TopicResponseDTOv1;
import br.com.dbserver.desafiovotacao.exceptions.TopicNotFoundException;
import br.com.dbserver.desafiovotacao.exceptions.TopicNotValidException;
import br.com.dbserver.desafiovotacao.services.v1.TopicService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/topics")
public class TopicController {
  private final TopicService service;

  @GetMapping
  public ResponseEntity<List<TopicResponseDTOv1>> list() {
    List<TopicResponseDTOv1> topics = service.getAllTopics();
    return new ResponseEntity<>(topics, HttpStatus.OK);
  }

  @GetMapping("{id}")
  public ResponseEntity<TopicResponseDTOv1> getV1(@PathVariable UUID id)
      throws TopicNotFoundException {
    TopicResponseDTOv1 topic = service.getTopic(id);
    return new ResponseEntity<>(topic, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<TopicResponseDTOv1> create(
      @Valid @RequestBody final TopicRequestDTOv1 request) throws TopicNotValidException {
    TopicResponseDTOv1 response = service.createTopic(request);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }
}
