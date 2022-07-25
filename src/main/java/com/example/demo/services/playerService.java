package com.example.demo.services;


import com.example.demo.models.playerModel;
import com.example.demo.repositories.playerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class playerService {
    @Autowired
    playerRepository playerRepository;

    public ArrayList<playerModel> findAll(){
        return (ArrayList<playerModel>) playerRepository.findAll();
    }

    public playerModel save(playerModel player){
        return playerRepository.save(player);
    }

    public Optional<playerModel> findPlayerById(Long id){
        return playerRepository.findById(id);
    }

    public void saveAllPlayers(List<playerModel> players){
        playerRepository.saveAll(players);
    }

    public Optional<ArrayList<playerModel>> getPlayersWithPagination(Long page, Long limit){
       Long pageV;
        if (page == 1){
            pageV = page;
        }else{
            pageV = (page*limit)-(limit-1);
        }
       return playerRepository.findByIdBetween( pageV, (pageV + limit)-1);
    }
}
