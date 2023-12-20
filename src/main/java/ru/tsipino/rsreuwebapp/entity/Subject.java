package ru.tsipino.rsreuwebapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@Entity
@AllArgsConstructor
@Table(name = "subjects")
public class Subject {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty(message = "Название не должно быть пустым")
  @Size(min = 2, max = 20, message = "Название должно иметь от 2 до 20 символов")
  private String title;

  @NotEmpty(message = "Описание не должно быть пустым")
  @Size(min = 10, max = 50, message = "Описание должно иметь от 2 до 50 символов")
  private String description;
}
