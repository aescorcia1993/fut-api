package com.example.demo.services;


import com.example.demo.models.nationModel;
import com.example.demo.repositories.nationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class nationService {
    @Autowired
    nationRepository nationRepository;

    public ArrayList<nationModel> findAll(){
        return (ArrayList<nationModel>) nationRepository.findAll();
    }

    public nationModel save(nationModel userProfile){
        return nationRepository.save(userProfile);
    }

    public Optional<nationModel> findUserProfileById(Long id){
        return nationRepository.findById(id);
    }

    public void borrarPorId(Long id){
           nationRepository.deleteById(id);
    }

    public void saveAllNations(List<nationModel> nations){
        nationRepository.saveAll(nations);
    }
}
