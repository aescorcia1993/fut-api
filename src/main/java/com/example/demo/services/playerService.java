package com.example.demo.services;


import com.example.demo.models.playerModel;
import com.example.demo.repositories.playerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class playerService {
    @Autowired
    playerRepository playerRepository;

    public ArrayList<playerModel> findAll(){
        return (ArrayList<playerModel>) playerRepository.findAll();
    }

    public playerModel save(playerModel userProfile){
        return playerRepository.save(userProfile);
    }

    public Optional<playerModel> findUserProfileById(Long id){
        return playerRepository.findById(id);
    }

    public void borrarPorId(Long id){
           playerRepository.deleteById(id);
    }

}
