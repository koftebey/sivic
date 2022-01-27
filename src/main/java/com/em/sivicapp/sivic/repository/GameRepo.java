package com.em.sivicapp.sivic.repository;

import com.em.sivicapp.sivic.model.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepo extends CrudRepository<Game, Long> {
    public Game findByName(@Param("name") String name);
}
