package ru.tsipino.rsreuwebapp.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tsipino.rsreuwebapp.entity.Subject;
import ru.tsipino.rsreuwebapp.repository.SubjectRepository;

@AllArgsConstructor
@Controller
@RequestMapping("/subjects")
public class SubjectController {

  SubjectRepository subjectRepository;

  @GetMapping("/")
  public String getMainPage(Model model) {
    model.addAttribute("subjectList", subjectRepository.findAll());
    return "SubjectMain";
  }

  @GetMapping("/new")
  public String getNewPage(@ModelAttribute("subject") Subject subject) {
    return "SubjectNew";
  }

  @PostMapping("/new")
  public String saveNew(
      @ModelAttribute("subject") @Valid Subject subject, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) return "SubjectNew";
    subjectRepository.save(subject);
    return "redirect:/subjects/";
  }
}
