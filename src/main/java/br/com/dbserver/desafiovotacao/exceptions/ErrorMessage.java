package br.com.dbserver.desafiovotacao.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {
  private String uri;
  private int status;
  private LocalDateTime timestamp;
  private String message;
  private Map<String, String> details;
  private String detail;
}
