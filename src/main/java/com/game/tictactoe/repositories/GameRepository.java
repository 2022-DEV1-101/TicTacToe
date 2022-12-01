package com.game.tictactoe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.game.tictactoe.entity.Game;

/**
 * @author boura
 * Repository for db interaction
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

}
