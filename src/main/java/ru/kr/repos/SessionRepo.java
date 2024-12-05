package ru.kr.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kr.domain.Hall;
import ru.kr.domain.Session;
import ru.kr.domain.Show;

import java.util.Date;
import java.util.List;

public interface SessionRepo extends JpaRepository<Session, Long> {
    List<Session> findByShow(Show show);
    List<Session> findByShowAndDateAfter(Show show, Date date);
    List<Session> findByShowIn(List<Show> shows);
    List<Session> findByShowInAndHall(List<Show> shows, Hall hall);
    List<Session> findByHall(Hall hall);

    List<Session> findByShowInAndHallAndDateAfter(List<Show> shows, Hall hall, Date date);

    List<Session> findByShowInAndDateAfter(List<Show> shows, Date date);

    List<Session> findByHallAndDateAfter(Hall hall, Date date);

    List<Session> findByDateAfter(Date date);
}
