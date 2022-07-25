package com.example.demo.controllers;

import com.example.demo.models.Post;
import com.example.demo.models.playerModel;
import com.example.demo.models.playerPageModel;
import com.example.demo.services.playerService;
import com.fasterxml.jackson.core.type.TypeReference;
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
@RequestMapping("api/v1/player")
public class playerController {
    @Autowired
    playerService playerService;

    @GetMapping("/findPlayers")
    public Optional<ArrayList<playerModel>> getPlayersWithPagination(@RequestParam(value="page") Long page,
                                                                     @RequestParam(value="limit") Long limit ){
        return playerService.getPlayersWithPagination(page,limit);
    }

    @GetMapping("/findAll")
    public ArrayList<playerModel> getPlayersProfile(){
        return playerService.findAll();
    }

    @PostMapping("/save")
    public playerModel savePlayerProfile(@RequestBody playerModel player){
        return this.playerService.save(player);
    }

    @GetMapping( path = "findById/{id}")
    public Optional<playerModel> findPlayerById(@PathVariable("id") Long id) {
        return this.playerService.findPlayerById(id);
    }

    @GetMapping("/updateAllPlayers")
    public String updateAllPlayers() throws IOException, InterruptedException, URISyntaxException {

        String url = "https://futdb.app/api/players";
        URI uri;

        for (int i=1; i < 1073; i ++) {
            try {
            uri = new URIBuilder(URI.create(url))
                    .addParameter("page", String.valueOf(i))
                    .addParameter("limit", "20")
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .header("accept", "application/json")
                    .header("X-AUTH-TOKEN", "7d16a9e7-2e7c-457f-8537-d2a81fbc550f")
                    .uri(uri)
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // parse JSON
            ObjectMapper mapper = new ObjectMapper();
            playerPageModel answer = mapper.readValue(response.body(), playerPageModel.class);

                List<playerModel> players = answer.items;
                this.playerService.saveAllPlayers(players);
                System.out.println("Last player page saved: "+ String.valueOf(i) );
            }catch (IOException e){
                System.out.println("Error in player page: "+ String.valueOf(i) );
                System.out.println("Error: "+ e );
                i = i-1;
            }

        }
        return "Players Updated";
    }

    @GetMapping("/testJson")
    public String testJson() throws IOException, InterruptedException {
        //Consulting how many pages does the API have?
        String url_test = "https://jsonplaceholder.typicode.com/posts";
        HttpClient client =  HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept","application/json")
                .uri(URI.create(url_test))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // parse JSON
        ObjectMapper mapper = new ObjectMapper();

        List<Post> posts = mapper.readValue(response.body(), new TypeReference<>() {});

        posts.forEach(post -> {
            System.out.println(post.toString());
        });
        posts.forEach(System.out::println);
        return "Ok";
    }
}