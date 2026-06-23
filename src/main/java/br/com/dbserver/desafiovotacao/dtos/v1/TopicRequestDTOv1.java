package br.com.dbserver.desafiovotacao.dtos.v1;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TopicRequestDTOv1 {
  @NotEmpty private String name;
  private String description;
}
