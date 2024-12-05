package ru.kr.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",  nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "film_session_id", nullable = false)
    private Session session;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "orders_seats",
            joinColumns = @JoinColumn(name = "order_id"), foreignKey = @ForeignKey(name = "fk_orders_seats"),
            inverseJoinColumns = @JoinColumn(name = "seat_id"), inverseForeignKey = @ForeignKey(name = "fk_seats_orders"))
    private Set<Seat> seats;

    @Column(name = "cost", nullable = false)
    private double cost;

    @Column(name = "purchase", nullable = false)
    private boolean purchase;
}
