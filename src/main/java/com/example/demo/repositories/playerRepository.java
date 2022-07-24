package com.example.demo.repositories;

import com.example.demo.models.playerModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface playerRepository extends CrudRepository<playerModel, Long> {
   // public abstract ArrayList<UserProfileModel> findByPrioridad(Integer prioridad);
}