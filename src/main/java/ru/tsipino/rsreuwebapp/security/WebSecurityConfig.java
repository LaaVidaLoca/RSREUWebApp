package ru.tsipino.rsreuwebapp.security;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.tsipino.rsreuwebapp.service.RSREUUserDetailService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final RSREUUserDetailService RSREUUserDetailService;

  @Bean
  public HttpFirewall getHttpFirewall() {
    StrictHttpFirewall firewall = new StrictHttpFirewall();
    firewall.setAllowedHttpMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
    return firewall;
  }

  @Bean
  public SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .csrf(
            httpSecurityCsrfConfigurer ->
                httpSecurityCsrfConfigurer.csrfTokenRepository(
                    CookieCsrfTokenRepository.withHttpOnlyFalse()))
        .authorizeHttpRequests(
            requests ->
                requests
                    .requestMatchers(new String[] {"/rsreu"})
                    .authenticated()
                    .requestMatchers(new String[] {"/lessons/"})
                    .authenticated()
                    .requestMatchers(new String[] {"/lessons/new"})
                    .authenticated()
                    .requestMatchers(new String[] {"/students/"})
                    .authenticated()
                    .requestMatchers(new String[] {"/students/individual"})
                    .authenticated()
                    .requestMatchers(new String[] {"/students/new"})
                    .authenticated()
                    .requestMatchers(new String[] {"/subjects/"})
                    .authenticated()
                    .requestMatchers(new String[] {"/subjects/new"})
                    .authenticated()
                    .requestMatchers("/css/Style.css")
                    .permitAll()
                    .requestMatchers("/images/rsreu.png")
                    .permitAll()
                    .requestMatchers(new String[] {"/registration"})
                    .permitAll())
        .formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/rsreu", true).permitAll())
        .logout(
            form ->
                form.logoutSuccessUrl("/login")
                    .logoutUrl("/logout")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .permitAll()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout")));
    return httpSecurity.build();
  }

  @Autowired
  protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(RSREUUserDetailService).passwordEncoder(bCryptPasswordEncoder);
  }
}
