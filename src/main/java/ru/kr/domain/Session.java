package ru.kr.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "sessions")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "session_id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;


    @Column(name = "price", nullable = false)
    private double price;

    @ToString.Exclude
    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    private Set<Order> orders;

    public Map<String, Boolean> getSeatStatus(Set<Order> orders, Hall hall) {
        Map<String, Boolean> seatStatus = new HashMap<>();

        // Инициализация всех мест как свободных
        for (int row = 1; row <= hall.getRows(); row++) {
            for (int seat = 1; seat <= hall.getSeatsPerRow(); seat++) {
                seatStatus.put(row + "-" + seat, false);
            }
        }

        // Обновление статуса занятых мест
        if (orders != null) {
            for (Order order : orders) {
                for (Seat seat : order.getSeats()) {
                    String key = seat.getRow() + "-" + seat.getPlace();
                    seatStatus.put(key, true);
                }
            }
        }

        return seatStatus;
    }
}
