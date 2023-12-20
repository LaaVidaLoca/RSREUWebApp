package ru.tsipino.rsreuwebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tsipino.rsreuwebapp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findUserByLogin(String login);

  boolean existsByLogin(String login);
}
