package eci.ieti.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Todo {

  @Id private Long id;

  private String description;
  private int priority;
  private LocalDate dueDate;
  private String responsible;
  private String status;

  @Override
  public String toString() {
    return "Product [id="
        + id
        + ", description="
        + description
        + ", priority="
        + priority
        + ", dueDate="
        + dueDate
        + ", responsible="
        + responsible
        + ", status="
        + status
        + "]";
  }
}
