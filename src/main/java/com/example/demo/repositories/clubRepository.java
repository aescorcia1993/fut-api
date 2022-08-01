package com.example.demo.repositories;

import com.example.demo.models.clubModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface clubRepository extends CrudRepository<clubModel, Long> {

    Optional<clubModel> findFirstByNameContainingIgnoreCase(String name);

    Optional<clubModel> findByNameContainingIgnoreCase(String name);



}