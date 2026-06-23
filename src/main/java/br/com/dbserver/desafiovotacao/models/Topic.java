package br.com.dbserver.desafiovotacao.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "topics")
public class Topic {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID topicId;

  @NotNull private String name;

  private String description;

  @NotNull private LocalDateTime created;
}
