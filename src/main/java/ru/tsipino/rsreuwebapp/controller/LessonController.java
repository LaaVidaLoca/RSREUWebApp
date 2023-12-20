package ru.tsipino.rsreuwebapp.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tsipino.rsreuwebapp.dto.LessonDTO;
import ru.tsipino.rsreuwebapp.entity.Lesson;
import ru.tsipino.rsreuwebapp.repository.LessonRepository;
import ru.tsipino.rsreuwebapp.repository.StudentRepository;
import ru.tsipino.rsreuwebapp.repository.SubjectRepository;

@Controller
@AllArgsConstructor
@RequestMapping("/lessons")
public class LessonController {

  LessonRepository lessonRepository;

  StudentRepository studentRepository;

  SubjectRepository subjectRepository;

  @GetMapping("/")
  public String getMainPage(Model model) {
    List<LessonDTO> lessonsViewList =
        lessonRepository.finAllLessonsView().stream().map(item -> new LessonDTO(item)).toList();
    model.addAttribute("lessonsViewList", lessonsViewList);
    return "LessonMain";
  }

  @GetMapping("/new")
  public String getNewPage(Model model, @ModelAttribute("lesson") Lesson lesson) {
    model.addAttribute("studentList", studentRepository.findAll());
    model.addAttribute("subjectList", subjectRepository.findAll());
    return "LessonNew";
  }

  @PostMapping("/new")
  public String saveNew(
      Model model,
      @ModelAttribute("lesson") @Valid Lesson lesson,
      BindingResult bindingResult,
      @ModelAttribute("studentId") Integer studentId,
      @ModelAttribute("subjectId") Integer subjectId) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("studentList", studentRepository.findAll());
      model.addAttribute("subjectList", subjectRepository.findAll());
      return "LessonNew";
    }
    lesson.setStudent(studentRepository.findFirstById(studentId));
    lesson.setSubject(subjectRepository.findFirstById(subjectId));
    lessonRepository.save(lesson);
    return "redirect:/lessons/";
  }
}
