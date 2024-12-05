package ru.kr.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kr.domain.Order;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long id);

}
