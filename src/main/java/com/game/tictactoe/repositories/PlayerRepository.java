package com.game.tictactoe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.game.tictactoe.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

}
