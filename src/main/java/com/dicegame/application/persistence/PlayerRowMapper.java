package com.dicegame.application.persistence;

import com.dicegame.application.domain.Player;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PlayerRowMapper implements RowMapper<Player> {

    @Override
    public Player mapRow(ResultSet rs, int rowNum) throws SQLException {

        Player player = new Player();
        player.setId(rs.getLong("id"));
        player.setName(rs.getString("name"));
        player.setHasGames(rs.getBoolean("has_games"));
        player.setSuccessRate(rs.getDouble("success_rate"));
     //   player.setCreatedAt(rs.getDate("created_at"));
        player.setCreatedAt(rs.getTimestamp("created_at"));
        
        return player;

    }
}