package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "clubs")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class clubModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long _id;

    private String name;
    private int id;
}

