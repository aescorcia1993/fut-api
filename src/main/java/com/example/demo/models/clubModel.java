package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "clubs")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class clubModel {

    @Id
    @Column(unique = true, nullable = false)
    private int id;

    private String name;

    public clubModel(int id) {
        this.id = id;
    }
}

