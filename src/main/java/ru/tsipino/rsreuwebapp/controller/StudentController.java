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
import ru.tsipino.rsreuwebapp.entity.Student;
import ru.tsipino.rsreuwebapp.repository.LessonRepository;
import ru.tsipino.rsreuwebapp.repository.StudentRepository;

@AllArgsConstructor
@Controller
@RequestMapping("/students")
public class StudentController {

  private StudentRepository studentRepository;
  private LessonRepository lessonRepository;

  @GetMapping("/")
  public String getMainPage(Model model) {
    List<Student> list = studentRepository.findAll();
    model.addAttribute("studentList", list);
    return "StudentMain";
  }

  @PostMapping("/individual")
  public String getIndividualPage(@ModelAttribute("studentId") Integer studentId, Model model) {
    model.addAttribute("student", studentRepository.findFirstById(studentId));
    model.addAttribute("subjectTitleList", lessonRepository.findVisitedSubjects(studentId));
    return "StudentIndividual";
  }

  @GetMapping("/new")
  public String getNewPage(@ModelAttribute("student") Student Student) {
    return "StudentNew";
  }

  @PostMapping("/new")
  public String saveNew(
      @ModelAttribute("student") @Valid Student student, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) return "StudentNew";
    studentRepository.save(student);
    return "redirect:/students/";
  }
}
