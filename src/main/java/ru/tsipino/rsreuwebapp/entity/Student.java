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
@Table(name = "students")
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty(message = "Имя не должно быть пустым")
  @Size(min = 2, max = 20, message = "Имя должно иметь от 2 до 20 символов")
  private String name;

  @NotEmpty(message = "Фамилия не должна быть пустым")
  @Size(min = 2, max = 20, message = "Фамилия должна иметь от 2 до 20 символов")
  private String surname;
}
