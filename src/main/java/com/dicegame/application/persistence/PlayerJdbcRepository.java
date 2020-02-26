package com.dicegame.application.persistence;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.dicegame.application.domain.Player;
import com.dicegame.application.exception.ResourceNotFoundException;
import com.mysql.cj.xdevapi.Result;



@Service
public class PlayerJdbcRepository implements PlayerRepository {
  
	@Autowired
	JdbcTemplate jdbcTemplate;

//    public Optional<Player> findOne(long playerId) {
//        return playerJdbcPersister.findOne(playerId);
//    }
//
//    public Result<Long, RepositoryFailure> create(Player player) {
//        try {
//            final long createPlayerId = playerJdbcPersister.create(player);
//            return Result.success(createPlayerId);
//        } catch (RepositoryException e) {
//            return RepositoryFailureFactory.failure(e, player);
//        }
//    }

	@Override
	public List<Player> findAll() {
		
        String sql = "SELECT * FROM players";

        List<Player> players = jdbcTemplate.query(
                sql,
                new PlayerRowMapper());

        return players;
		
    }
	
//	@Override
//	public S save(Player player) {
//
//		
//		jdbcTemplate.update(
//			    "INSERT INTO players (name) VALUES (?)",
//			    player.getName()
//			);
//	}

	@Override
	public List<Player> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Player> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Player> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Player> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<Player> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Player getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Player> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Player> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Player> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Player> S save(S entity) {
		Timestamp timestamp = new Timestamp(new Date().getTime());
		entity.setCreatedAt(timestamp);
	jdbcTemplate.update(
		    "INSERT INTO players (created_at, has_games, name, success_rate) VALUES (?, ?, ?, ?)",
		    entity.getCreatedAt(), entity.isHasGames(), entity.getName(), entity.getSuccessRate()
		);

	return entity;
	
	}
	

	public <S extends Player> S update(S entity) {
		//Timestamp timestamp = new Timestamp(new Date().getTime());
		//entity.setCreatedAt(timestamp);
	jdbcTemplate.update(
			"update players set name = ? where id = ?",
		    entity.getName(), entity.getId()
		);

	return entity;
	
	}
	
	/*
	 * public <S extends Player> S update(S entity) { Timestamp timestamp = new
	 * Timestamp(new Date().getTime()); entity.setCreatedAt(timestamp);
	 * jdbcTemplate.update(
	 * "INSERT INTO players (created_at, has_games, name, success_rate) VALUES (?, ?, ?, ?)"
	 * , entity.getCreatedAt(), entity.isHasGames(), entity.getName(),
	 * entity.getSuccessRate() );
	 * 
	 * return entity;
	 * 
	 * }
	 */

//	@Override
//	public Optional<Player> findById(Long id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	

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
	public void delete(Player entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Player> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Player> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Player> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Player> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Player> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public Optional<Player> findById(Long id) {

		 String sql = "SELECT * FROM players WHERE id = ?";

	        return Optional.of(jdbcTemplate.queryForObject(sql, new Object[]{id}, new PlayerRowMapper()));
	}



//	@Override
//	public Optional<Player> findById(Long id) {
//		  String sql = "SELECT * FROM player WHERE id = ?";
//
//	        return (Optional<Player>) jdbcTemplate.queryForObject(
//				sql, 
//				new Object[]{id}, 
//				new BeanPropertyRowMapper(Player.class));
//	}

    // Other functions eg. findAll, delete, etc....
}