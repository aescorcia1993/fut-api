package com.example.demo.repositories;

import com.example.demo.models.clubModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface clubRepository extends CrudRepository<clubModel, Long> {

}