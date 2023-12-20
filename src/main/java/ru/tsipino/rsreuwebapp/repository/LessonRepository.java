package ru.tsipino.rsreuwebapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tsipino.rsreuwebapp.entity.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
  @Query(
      "SELECT DISTINCT sb.title"
          + " FROM Lesson l"
          + " JOIN l.subject sb"
          + " JOIN l.student st"
          + " WHERE st.id = :studentId")
  List<String> findVisitedSubjects(Integer studentId);

  @Query(
      "SELECT l.auditorium, st.surname, sb.title "
          + "FROM Lesson l "
          + "JOIN l.subject sb "
          + "JOIN l.student st")
  List<Object[]> finAllLessonsView();
}
