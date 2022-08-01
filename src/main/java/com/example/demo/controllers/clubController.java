package com.example.demo.controllers;

import com.example.demo.models.*;
import com.example.demo.services.clubService;
import com.example.demo.services.playerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/team")
public class clubController {
    @Autowired
    clubService clubService;

    @Autowired
    playerService playerService;

    @PostMapping("/")
    public Object getPlayersByClub(@RequestBody bodyClub _body){
        String nameLC = StringUtils.lowerCase(_body.Name);
        Optional<ArrayList<playerModel>> players =  clubService.findPlayersByTeam(nameLC,_body.Page);

        wrapperAnswer<playerModelAnswer> answer = new wrapperAnswer<>();
        List<playerModelAnswer> foundPlayers = new ArrayList<>();
            answer.count = players.map(List::size).orElse(0);
            answer.count_total = players.map(List::size).orElse(0);
            answer.page = _body.Page;
            answer.page_total = (int) Math.ceil(players.map(List::size).orElse(0) / 20);
            answer.items_per_page = 20;

        if (players.isPresent()){
            players.ifPresent(p -> {
                p.forEach(l-> {
                    playerModelAnswer _player = new playerModelAnswer();
                    _player.setName(l.getName());
                    _player.setNation(l.getNation().getName());
                    _player.setPosition(l.getPosition());
                    foundPlayers.add(_player);
                });
            });
        }
            answer.items = foundPlayers;
        return answer;
    }

    @GetMapping("/findAll")
    public ArrayList<clubModel> getAllClubs(){
        return clubService.findAll();
    }

    @PostMapping("/save")
    public clubModel saveClub(@RequestBody clubModel club){
        return this.clubService.save(club);
    }

    @GetMapping( path = "findById/{id}")
    public Optional<clubModel> findClubById(@PathVariable("id") Long id) {
        return this.clubService.findClubById(id);
    }

    @GetMapping("/updateAllClubs")
    public String updateAllClub() throws IOException, InterruptedException, URISyntaxException {
        //Consulting how many pages does the API have?
        String url = "https://futdb.app/api/clubs";
        URI uri;
        for (int i=1; i < 35; i ++){
            uri = new URIBuilder(URI.create(url))
                    .addParameter("page", String.valueOf(i))
                    .addParameter("limit", "20")
                    .build();

            HttpClient client =  HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .header("accept","application/json")
                    .header("X-AUTH-TOKEN","7d16a9e7-2e7c-457f-8537-d2a81fbc550f")
                    .uri(uri)
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // parse JSON
            ObjectMapper mapper = new ObjectMapper();
            clubPageModel answer = mapper.readValue(response.body(), clubPageModel.class);

            List<clubModel> nations = answer.items;
            this.clubService.saveAllClubs(nations);
        }


        return "Clubs Updated";
    }


}