package com.dicegame.application.controller;


import com.dicegame.application.exception.ResourceNotFoundException;
import com.dicegame.application.domain.Game;
import com.dicegame.application.domain.Player;
import com.dicegame.application.persistence.GameJdbcRepository;
import com.dicegame.application.persistence.GameRepository;
import com.dicegame.application.persistence.PlayerJdbcRepository;
import com.dicegame.application.persistence.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class GameController {

	@Autowired
	private GameJdbcRepository gameRepository;

	@Autowired
	private PlayerJdbcRepository playerRepository;

	@CrossOrigin(origins = "http://localhost")
	@GetMapping("/games")
	public List<Game> getAllGames() { //Page<Player> getAllPlayers(Pageable pageable)
		return gameRepository.findAll();
	}
	
	@CrossOrigin(origins = "http://localhost")
	@GetMapping("/games/{gameId}")
	Game one(@PathVariable Long gameId) {
	
	return gameRepository.findById(gameId)
				.orElseThrow(() -> new ResourceNotFoundException("GameId " + gameId + " not found"));
	}
	//	
	
	@CrossOrigin(origins = "http://localhost")
	@GetMapping("/players/{playerId}/games")
	public List<Game> getAllGamesByPlayerId(@PathVariable (value = "playerId") Long playerId,   //Page<Game>
			Pageable pageable) {
		return gameRepository.findByPlayerId(playerId, null);  //pageable
	}

	@CrossOrigin(origins = "http://localhost")
	@PostMapping("/players/{playerId}/games")
	public Game createGame(@PathVariable (value = "playerId") Long playerId, Game game)  { //@RequestBody Game game


		
		return playerRepository.findById(playerId).map(player -> {  
			game.setPlayer(player);

			int dice1 = (@NotNull int) Math.floor(Math.random()*6+1);
			int dice2 = (@NotNull int) Math.floor(Math.random()*6+1);
			game.setDice1(dice1);
			game.setDice2(dice2);

			if (game.getDice1() + game.getDice2() == 7) {
				game.setHasWon(true); 
			}



			double points = 0;
			double successRate;
			List<Game> gamesByPlayer = new ArrayList<Game>();
			gamesByPlayer = gameRepository.findByPlayerId(player.getId(), null);  //.getContent()

			//suma els jocs anteriors
			for (int i=0; i<gamesByPlayer.size(); i++) {
				if(gamesByPlayer.get(i).isHasWon()) {
					points += 100;
				}
			}

			//i falta el joc actual

			if (game.isHasWon()) {
				points += 100;
			}

			successRate = Math.round((points / (gamesByPlayer.size()+1)) * 100) / 100d;

			// 	game.getPlayer().setSuccessRate(successRate);
			player.setSuccessRate(successRate);

			// if (game.getAuthor()=="") {
			//  picture.setAuthor("ANONYMOUS");
			//  };
			//  shop.setNumPictures(shop.getNumPictures()+1);
			player.setHasGames(true); 
			playerRepository.update(player);
			//return gameRepository.save(game);
			gameRepository.save(game);
			return gameRepository.findAll().get(gameRepository.findAll().size()-1);//return game;
		}).orElseThrow(() -> new ResourceNotFoundException("PlayerId " + playerId + " not found"));


	}


	//	@CrossOrigin(origins = "http://localhost")
	//    @PutMapping("/shops/{shopId}/pictures/{pictureId}")
	//    public Picture updatePicture(@PathVariable (value = "shopId") Long shopId,
	//                                 @PathVariable (value = "pictureId") Long pictureId,
	//                                 @Valid @RequestBody Picture pictureRequest) {
	//        if(!shopRepository.existsById(shopId)) {
	//            throw new ResourceNotFoundException("ShopId " + shopId + " not found");
	//        }
	//
	//        return pictureRepository.findById(pictureId).map(picture -> {
	//        	picture.setAuthor(pictureRequest.getAuthor());
	//            picture.setName(pictureRequest.getName());
	//            picture.setPrice(pictureRequest.getPrice());
	//            return pictureRepository.save(picture);
	//        }).orElseThrow(() -> new ResourceNotFoundException("PictureId " + pictureId + "not found"));
	//    }



	@CrossOrigin(origins = "http://localhost")
	@DeleteMapping("/players/{playerId}/games")
	public void deleteAllGamesByPlayerId(@PathVariable (value = "playerId") Long playerId) {

		gameRepository.deleteAll(gameRepository.findByPlayerId(playerId, null));
		//POSAR RATE A NULL:
		Player player = playerRepository.getOne(playerId);
		player.setSuccessRate(0);
		player.setHasGames(false);
		playerRepository.update(player);  //save
	}



}