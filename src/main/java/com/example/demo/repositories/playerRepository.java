package com.example.demo.repositories;

import com.example.demo.models.playerModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface playerRepository extends CrudRepository<playerModel, Long> {

    Optional<ArrayList<playerModel>> findByIdBetween(Long idStart, Long idEnd);

    Optional<ArrayList<playerModel>> findByClub_Id(int id);





    @Query("SELECT m.name, c.name FROM playerModel m INNER JOIN clubModel c ON c.id = :club and m.club = :club")
    List<Object> innerJoinClub_PlayerByClubId(@Param("club") int club);

    Optional<ArrayList<playerModel>> findByClub_IdEquals(int id);


    // SELECT fifa.players.name, fifa.players.position, fifa.clubs.names
   // FROM fifa.players
   // INNER JOIN fifa.clubs ON fifa.players.club = fifa.clubs.id
}