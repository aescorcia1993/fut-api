package com.example.demo.repositories;

import com.example.demo.models.nationModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface nationRepository extends CrudRepository<nationModel, Long> {

}