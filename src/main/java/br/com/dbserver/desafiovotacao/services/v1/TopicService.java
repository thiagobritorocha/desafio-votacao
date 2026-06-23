package br.com.dbserver.desafiovotacao.services.v1;

import br.com.dbserver.desafiovotacao.converters.TopicConverter;
import br.com.dbserver.desafiovotacao.dtos.v1.TopicRequestDTOv1;
import br.com.dbserver.desafiovotacao.dtos.v1.TopicResponseDTOv1;
import br.com.dbserver.desafiovotacao.exceptions.TopicNotFoundException;
import br.com.dbserver.desafiovotacao.exceptions.TopicNotValidException;
import br.com.dbserver.desafiovotacao.models.Topic;
import br.com.dbserver.desafiovotacao.repositories.TopicRepository;
import jakarta.persistence.EntityExistsException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopicService {
  private final TopicRepository repository;
  private final TopicConverter converter;

  public List<TopicResponseDTOv1> getAllTopics() {
    return converter.toResponseDTOv1(repository.findAll());
  }

  public TopicResponseDTOv1 getTopic(UUID id) throws TopicNotFoundException {
    Optional<Topic> topic = repository.findById(id);
    return converter.toResponseDTOv1(topic.orElseThrow(() -> new TopicNotFoundException(id)));
  }

  public TopicResponseDTOv1 createTopic(TopicRequestDTOv1 request) throws TopicNotValidException {
    Topic topic = converter.toModel(request);
    setCreationTimestamp(topic);
    try {
      topic = repository.saveAndFlush(topic);
    } catch (EntityExistsException ex) {
      throw new TopicNotValidException("Topic already exists: " + request.getName());
    }
    return converter.toResponseDTOv1(topic);
  }

  private void setCreationTimestamp(Topic topic) {
    topic.setCreated(LocalDateTime.now());
  }
}
