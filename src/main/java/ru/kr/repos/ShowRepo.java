package ru.kr.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.kr.domain.Show;

import java.util.List;
import java.util.Optional;

public interface ShowRepo extends JpaRepository<Show, Long >, JpaSpecificationExecutor<Show> {
    List<Show> findByTitle(String title);
    Show findByTitleAndTitle(String title, String title1);
    List<Show> findByGanre(Optional<Object> ganre);
    List<Show> findByTitleAndGanre(String title, Optional<Object> ganre);
}
