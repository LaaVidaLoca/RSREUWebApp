package ru.tsipino.rsreuwebapp.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.tsipino.rsreuwebapp.entity.User;
import ru.tsipino.rsreuwebapp.repository.UserRepository;

@AllArgsConstructor
@Controller
public class RSREUController {
  private final UserRepository userRepository;

  @GetMapping("/rsreu")
  public String getHomePage(Model model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userRepository.findUserByLogin(auth.getName());
    model.addAttribute("user", user);
    return "Home";
  }
}
