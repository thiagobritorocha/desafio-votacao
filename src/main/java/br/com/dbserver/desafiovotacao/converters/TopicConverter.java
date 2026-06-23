package br.com.dbserver.desafiovotacao.converters;

import br.com.dbserver.desafiovotacao.dtos.v1.TopicRequestDTOv1;
import br.com.dbserver.desafiovotacao.dtos.v1.TopicResponseDTOv1;
import br.com.dbserver.desafiovotacao.models.Topic;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TopicConverter {
  public Topic toModel(TopicRequestDTOv1 dto) {
    Topic model = new Topic();
    model.setName(dto.getName());
    model.setDescription(dto.getDescription());
    return model;
  }

  public TopicResponseDTOv1 toResponseDTOv1(Topic model) {
    return TopicResponseDTOv1.builder()
        .id(model.getTopicId())
        .name(model.getName())
        .description(model.getDescription())
        .created(model.getCreated())
        .build();
  }

  public List<TopicResponseDTOv1> toResponseDTOv1(List<Topic> model) {
    List<TopicResponseDTOv1> dto = new ArrayList<>();
    model.forEach(
        topic -> {
          dto.add(toResponseDTOv1(topic));
        });
    return dto;
  }
}
