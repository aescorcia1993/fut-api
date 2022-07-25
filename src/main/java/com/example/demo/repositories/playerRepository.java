package com.example.demo.repositories;

import com.example.demo.models.playerModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface playerRepository extends CrudRepository<playerModel, Long> {

    Optional<ArrayList<playerModel>> findByIdBetween(Long idStart, Long idEnd);

}