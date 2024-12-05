package ru.kr.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "shows")
public class Show {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private Integer duration;
    @Column(name = "age_rating", nullable = true)
    private Integer ageRating;

    @ManyToOne
    @JoinColumn(name = "ganre_id", nullable = false)
    private Ganre ganre;
    private String filename;
    @Column(length = 1000)
    private String description;
    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Session> sessions = new ArrayList<>();

    public Show() {
    }

   public String getGanre(){
        return ganre != null ? ganre.getName() : "none";
   }
    public String getAgeRating() {
        return ageRating != null ? String.valueOf(ageRating) : "none";
    }

    public Show(String title,Integer duration, Integer ageRating, Ganre ganre) {
        this.title = title;
        this.duration = duration;
        this.ageRating = ageRating;
        this.ganre = ganre;
    }
}
