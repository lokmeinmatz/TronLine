package main;

import Utils.Vector2f;

public class Player {
	
	private int[] color;
	private String playername;
	private Vector2f location, velocity;
	private int deaths;
	private PlayerState playerstate;
	
	public enum PlayerState{
		JOINING, LOBBY, PLAYING, DEAD
	}
	
	public void setPlayerState(PlayerState state){
		this.playerstate = state;
	}

	public Player(int[] color, String playername, Vector2f location, Vector2f velocity, int deaths) {
		this.color = color;
		this.playername = playername;
		this.location = location;
		this.velocity = velocity;
		this.deaths = deaths;
		setPlayerState(PlayerState.JOINING);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Setters n Getters
	public int[] getColor() {
		return color;
	}

	public void setColor(int[] color) {
		this.color = color;
	}

	public String getPlayername() {
		return playername;
	}

	public void setPlayername(String playername) {
		this.playername = playername;
	}

	public Vector2f getLocation() {
		return location;
	}

	public void setLocation(Vector2f location) {
		this.location = location;
	}

	public Vector2f getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2f velocity) {
		this.velocity = velocity;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public PlayerState getPlayerstate() {
		return playerstate;
	}
	
	
	
	
	

}
