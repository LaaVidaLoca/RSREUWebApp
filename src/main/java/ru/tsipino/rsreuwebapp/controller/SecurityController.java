package ru.tsipino.rsreuwebapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.tsipino.rsreuwebapp.entity.User;
import ru.tsipino.rsreuwebapp.repository.UserRepository;

@Controller
@RequiredArgsConstructor
public class SecurityController {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @GetMapping("/login")
  public String login() {
    if (SecurityContextHolder.getContext().getAuthentication() != null
        && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
        && !(SecurityContextHolder.getContext().getAuthentication()
            instanceof AnonymousAuthenticationToken)) {
      return "redirect:/rsreu";
    }
    return "login";
  }

  @GetMapping("/registration")
  public String registration(Model model) {
    model.addAttribute("user", new User());
    return "register";
  }

  @PostMapping("/registration")
  public String registration(
      @ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
    if (userRepository.existsByLogin(user.getLogin())) {
      bindingResult.addError(
          new FieldError("user", "login", "Пользователь с таким логином уже зарегистрирован"));
      return "register";
    }
    if (bindingResult.hasErrors()) {
      return "register";
    }
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    userRepository.save(user);
    return "redirect:/login";
  }
}
