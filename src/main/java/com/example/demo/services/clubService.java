package com.example.demo.services;


import com.example.demo.models.clubModel;
import com.example.demo.repositories.clubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class clubService {
    @Autowired
    clubRepository clubRepository;

    public ArrayList<clubModel> findAll(){
        return (ArrayList<clubModel>) clubRepository.findAll();
    }

    public clubModel save(clubModel userProfile){
        return clubRepository.save(userProfile);
    }

    public Optional<clubModel> findUserProfileById(Long id){
        return clubRepository.findById(id);
    }

    public void borrarPorId(Long id){
           clubRepository.deleteById(id);
    }

    public void saveAllClubs(List<clubModel> clubs){
        clubRepository.saveAll(clubs);
    }
}
