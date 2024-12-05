package ru.kr.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kr.domain.User;

public interface UsersRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
