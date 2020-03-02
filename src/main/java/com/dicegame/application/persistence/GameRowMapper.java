package com.dicegame.application.persistence;

import com.dicegame.application.domain.Game;
import com.dicegame.application.domain.Player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class GameRowMapper implements RowMapper<Game> {

	@Autowired
	private GameJdbcRepository gameRepository;
	
    @Override
    public Game mapRow(ResultSet rs, int rowNum) throws SQLException {

        Game game = new Game();
        Player player = game.getPlayer();
     
        game.setId(rs.getLong("id"));
        game.setDice1(rs.getInt("dice1"));
        game.setDice2(rs.getInt("dice2"));
        game.setHasWon(rs.getBoolean("has_won"));
       // game.setCreatedAt(rs.getTimestamp("created_at"));
        game.setCreatedAt(rs.getString("created_at"));
       // player.setId(rs.getLong("player_id"));
       // game.setPlayerId(rs.getLong("player_id"));
        
	//	game.setPlayer((Player) gameRepository.findByPlayerId(rs.getLong("player_id"), null));
       // gameRepository.findByPlayerId(playerId, pageable)
        
        
      
        
        
        return game;

    }
}