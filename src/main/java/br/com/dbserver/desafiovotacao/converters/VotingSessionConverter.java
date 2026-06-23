package br.com.dbserver.desafiovotacao.converters;

import br.com.dbserver.desafiovotacao.dtos.v1.VotingSessionRequestDTOv1;
import br.com.dbserver.desafiovotacao.dtos.v1.VotingSessionResponseDTOv1;
import br.com.dbserver.desafiovotacao.models.Topic;
import br.com.dbserver.desafiovotacao.models.VotingSession;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VotingSessionConverter {
  @Autowired private TopicConverter topicConverter;

  public VotingSession toModel(VotingSessionRequestDTOv1 dto) {
    VotingSession model = new VotingSession();
    model.setExpires(dto.getExpires());

    Topic topic = new Topic();
    topic.setTopicId(dto.getTopicId());
    model.setTopic(topic);

    return model;
  }

  public VotingSessionResponseDTOv1 toResponseDTOv1(VotingSession model) {
    return VotingSessionResponseDTOv1.builder()
        .id(model.getVotingSessionId())
        .topic(topicConverter.toResponseDTOv1(model.getTopic()))
        .expires(model.getExpires())
        .build();
  }

  public List<VotingSessionResponseDTOv1> toResponseDTOv1(List<VotingSession> model) {
    List<VotingSessionResponseDTOv1> dto = new ArrayList<>();
    model.forEach(
        session -> {
          dto.add(toResponseDTOv1(session));
        });
    return dto;
  }
}
