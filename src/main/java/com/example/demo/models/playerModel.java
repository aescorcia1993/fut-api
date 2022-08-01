package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "players")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class playerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String name;
    private String position;

    @ManyToOne
    @JoinColumn(name = "nation_id")
    private nationModel nation;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private clubModel club;


    public void setNation(int id) {
        this.nation = new nationModel(id);
    }

    public void setClub(int id) {
        this.club = new clubModel(id);
    }
}

