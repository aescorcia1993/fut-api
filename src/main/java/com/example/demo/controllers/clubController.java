package com.example.demo.controllers;

import com.example.demo.models.clubModel;
import com.example.demo.models.clubPageModel;
import com.example.demo.services.clubService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
@RequestMapping("/api/v1/club")
public class clubController {
    @Autowired
    clubService clubService;

    @GetMapping("/findAll")
    public ArrayList<clubModel> getAllNations(){
        return clubService.findAll();
    }

    @PostMapping("/save")
    public clubModel saveNation(@RequestBody clubModel nation){
        return this.clubService.save(nation);
    }

    @GetMapping( path = "findById/{id}")
    public Optional<clubModel> findNationById(@PathVariable("id") Long id) {
        return this.clubService.findUserProfileById(id);
    }

    @GetMapping("/updateAllClubs")
    public String updateAllNations() throws IOException, InterruptedException, URISyntaxException {
        //Consulting how many pages does the API have?
        String url = "https://futdb.app/api/clubs";
        URI uri;
        for (int i=1; i < 34; i ++){
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