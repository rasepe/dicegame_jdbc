package com.dicegame.application.persistence;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.dicegame.application.domain.Game;
import com.dicegame.application.domain.Player;

@Service
public class GameJdbcRepository implements GameRepository  {

	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Game> findAll() {
		
        String sql = "SELECT * FROM games";

        List<Game> games = jdbcTemplate.query(
                sql,
                new GameRowMapper());

        return games;
		
    }
	


	@Override
	public List<Game> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Game> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Game> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Game> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<Game> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Game getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Game> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Game> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Game> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override   
	public <S extends Game> S save(S entity) {
		Timestamp timestamp = new Timestamp(new Date().getTime());
		entity.setCreatedAt(timestamp);
	jdbcTemplate.update(
		    "INSERT INTO games (created_at, dice1, dice2, has_won, player_id) VALUES (?, ?, ?, ?, ?)",
		    entity.getCreatedAt(), entity.getDice1(), entity.getDice2(), entity.isHasWon(), entity.getPlayer().getId()
		);

	return entity;
	}

	@Override
	public Optional<Game> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Game entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Game> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Game> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Game> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Game> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Game> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Game> findByPlayerId(Long playerId, Pageable pageable) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM games WHERE player_id =" + playerId;

		List<Game> games = jdbcTemplate.query(
                sql,
                new GameRowMapper());

        return games;
	}

	@Override
	public Game findByPlayerId(Long playerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
