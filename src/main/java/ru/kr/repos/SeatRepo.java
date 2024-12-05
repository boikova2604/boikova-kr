package ru.kr.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kr.domain.Hall;
import ru.kr.domain.Seat;

public interface SeatRepo extends JpaRepository<Seat, Long> {
    Seat findByRowAndPlaceAndHall(Byte row, Byte place, Hall hall);
}
