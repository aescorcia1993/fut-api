package com.example.demo.services;


import com.example.demo.models.clubModel;
import com.example.demo.models.playerModel;
import com.example.demo.repositories.clubRepository;
import com.example.demo.repositories.playerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class clubService {
    private playerRepository playerRepository;

    @Autowired
    clubRepository clubRepository;

    public clubService(playerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    public ArrayList<clubModel> findAll(){
        return (ArrayList<clubModel>) clubRepository.findAll();
    }

    public clubModel save(clubModel club){
        return clubRepository.save(club);
    }

    public Optional<clubModel> findClubById(Long id){
        return clubRepository.findById(id);
    }

    public void deleteClubById(Long id){
           clubRepository.deleteById(id);
    }

    public void saveAllClubs(List<clubModel> clubs){
        clubRepository.saveAll(clubs);
    }

    public Optional<ArrayList<playerModel>> findPlayersByTeam(String name, int page){
        Optional<clubModel> club = clubRepository.findFirstByNameContainingIgnoreCase(name);
        //Optional<ArrayList<playerModel>> players = playerRepository.findByClub_IdEquals(club.get().getId());
        Optional<ArrayList<playerModel>> players2 = playerRepository.findByClub_Id(club.get().getId());
        System.out.println("**********************************");
        System.out.println(players2);
        return players2;
    }
}
