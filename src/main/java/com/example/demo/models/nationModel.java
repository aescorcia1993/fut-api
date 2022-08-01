package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "nations")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class nationModel {

    @Id
    @Column(unique = true, nullable = false)
    private int id;

    private String name;

    public nationModel(int id){
        this.id = id;
    }

}

