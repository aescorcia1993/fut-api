package com.example.demo.controllers;

import com.example.demo.models.nationModel;
import com.example.demo.models.nationPageModel;
import com.example.demo.services.nationService;
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
@RequestMapping("/nation")
public class nationController {
    @Autowired
    nationService nationService;

    @GetMapping("/findAll")
    public ArrayList<nationModel> getAllNations(){
        return nationService.findAll();
    }

    @PostMapping("/save")
    public nationModel saveNation(@RequestBody nationModel nation){
        return this.nationService.save(nation);
    }

    @GetMapping( path = "findById/{id}")
    public Optional<nationModel> findNationById(@PathVariable("id") Long id) {
        return this.nationService.findUserProfileById(id);
    }

    @GetMapping("/updateAllNations")
    public String updateAllNations() throws IOException, InterruptedException, URISyntaxException {
        //Consulting how many pages does the API have?
        String url = "https://futdb.app/api/nations";
        URI uri;

        uri = new URIBuilder(URI.create(url))
                .addParameter("page", "1")
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
        nationPageModel answer = mapper.readValue(response.body(), nationPageModel.class);

        List<nationModel> nations = answer.items;
        this.nationService.saveAllNations(nations);

        return "Nations Updated";
    }


}