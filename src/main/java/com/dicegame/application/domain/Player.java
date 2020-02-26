package com.dicegame.application.domain;



import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Value;



@Entity
@Table(name = "players")
public class Player { // extends AuditModel


	/**
	 * 
	 */
	//private static long COUNTER = 1L;


	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

   // @NotNull
   // @Size(max = 100)
    
    @Column(unique = true)
    private String name;
    
    private double successRate;

	//private LocalDateTime createdAt;

    private Timestamp createdAt;
    
	boolean hasGames = false;
    
	public Player(String name) {
		//this.id = COUNTER;
		//COUNTER++;
		this.name = name;
		this.createdAt = new Timestamp(System.currentTimeMillis());
	}
	
	public Player() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSuccessRate() {
		return successRate;
	}

	public void setSuccessRate(double successRate) {
		this.successRate = successRate;
	}

//	public LocalDateTime getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(LocalDateTime createdAt) {
//		this.createdAt = createdAt;
//	}
	
//	public Date getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(Date createdAt) {
//		this.createdAt = createdAt;
//	}

	public boolean isHasGames() {
		return hasGames;
	}

	public void setHasGames(boolean hasGames) {
		this.hasGames = hasGames;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}



	/*
	 * public Optional<Player> orElseThrow(Object object) { // TODO Auto-generated
	 * method stub return null; }
	 * 
	 * public Player map(Object object) { // TODO Auto-generated method stub return
	 * null; }
	 */







    
  
}