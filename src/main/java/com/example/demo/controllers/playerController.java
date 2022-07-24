package com.example.demo.controllers;

import com.example.demo.models.playerModel;
import com.example.demo.services.playerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;


@RestController
@RequestMapping("/player")
public class playerController {
    @Autowired
    playerService playerService;

    @GetMapping("/findAll")
    public ArrayList<playerModel> getUsersProfile(){
        return playerService.findAll();
    }

    @PostMapping("/save")
    public playerModel saveUserProfile(@RequestBody playerModel userProfile){
        return this.playerService.save(userProfile);
    }

    @GetMapping( path = "findById/{id}")
    public Optional<playerModel> findUserProfileById(@PathVariable("id") Long id) {
        return this.playerService.findUserProfileById(id);
    }

}