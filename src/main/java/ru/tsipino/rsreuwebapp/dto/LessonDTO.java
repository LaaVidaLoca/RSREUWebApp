package ru.tsipino.rsreuwebapp.dto;

import lombok.Data;

@Data
public class LessonDTO {
  Integer auditorium;
  String studentSurname;
  String subjectTitle;

  public LessonDTO(Object[] data) {
    this.auditorium = (Integer) data[0];
    this.studentSurname = (String) data[1];
    this.subjectTitle = (String) data[2];
  }
}
