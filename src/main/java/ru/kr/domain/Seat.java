package ru.kr.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "seats")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "seat_id")
    private Long id;

    @Column(name = "row", nullable = false)
    private Byte row;

    @Column(name = "place", nullable = false)
    private Byte place;
    @ManyToOne
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hall;
}