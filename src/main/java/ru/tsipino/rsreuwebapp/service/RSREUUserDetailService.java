package ru.tsipino.rsreuwebapp.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.tsipino.rsreuwebapp.dto.RSREUUserDetails;
import ru.tsipino.rsreuwebapp.entity.User;
import ru.tsipino.rsreuwebapp.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class RSREUUserDetailService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = userRepository.findUserByLogin(username);
    List<GrantedAuthority> authorities = new ArrayList<>();
    String role = "ROLE_USER";
    authorities.add(new SimpleGrantedAuthority(role));
    return new RSREUUserDetails(user.getId().intValue(), username, user.getPassword(), authorities);
  }
}
