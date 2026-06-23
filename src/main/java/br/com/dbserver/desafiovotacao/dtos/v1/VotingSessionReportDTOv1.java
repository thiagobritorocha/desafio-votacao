package br.com.dbserver.desafiovotacao.dtos.v1;

import br.com.dbserver.desafiovotacao.enums.VoteResult;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VotingSessionReportDTOv1 {
  private UUID id;
  private TopicResponseDTOv1 topic;
  private Boolean expired;
  private Long yes;
  private Long no;
  private VoteResult result;
  private List<VoteResponseDTOv1> votes;
}
