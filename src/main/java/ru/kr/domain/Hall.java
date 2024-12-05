package ru.kr.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "halls")
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hall_id")
    private Long Id;
    private String name;
    private int rows;
    @Column(name = "seats_per_row")
    private int seatsPerRow;
    @Column(nullable = false)
    private int seatCapacity;
    private String description;



    @PrePersist
    @PreUpdate
    private void calculateSeatCapacity() {
        this.seatCapacity = rows * seatsPerRow;
    }
}
