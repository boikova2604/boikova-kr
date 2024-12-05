package ru.kr.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kr.domain.Hall;

public interface HallRepo extends JpaRepository<Hall, Long> {
    Hall findByName (String name);

}
