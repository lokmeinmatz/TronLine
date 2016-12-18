package main;

import java.util.ArrayList;
import java.util.List;

import graphics.Renderer;
import javafx.animation.AnimationTimer;
import networking.Client;

public class GameLoop{

	
	private double lastframe, secondtimer;
	private long startNanoTime;
	private String playername;
	private Client netclient;
	private int[] color;
	private int[] dimensions;
	private Renderer r;
	private GameState gamestate;
	private List<Player> players; 
	
	private enum GameState{
		LOADING, CONNECTING, WAITING, PLAYING, WINNERSCREEN, RESET
	}
	
	private void setGameState(GameState state){
		this.gamestate = state;
		switch (state) {
		case LOADING:
			//TODO do loading stuff
			break;
			
		case CONNECTING:
			//TODO connecting screen
			
			break;
			
		case PLAYING:
			//TODO init playing
			break;
			
		case RESET:
			//TODO do reset
			break;
			
		case WAITING:
			
			r.setUpStartScreen();
			
			break;
			
		default:
			break;
		}
	}
	
	
	public GameLoop(String playername, Client netclient, int[] color, boolean isServer, int width, int height, String ip) {
		
		setGameState(GameState.LOADING);
		players = new ArrayList<>();
		players.add(new Player(color,  playername, null, null, 0));
		//players.add(new Player(new int[]{100, 200, 0},  "Lztguztuztutz", null, null, 0));
		
		this.netclient = netclient;
		this.playername = playername;
		this.netclient = netclient;
		this.dimensions = new int[] {width, height};
		this.color = color;
		r = new Renderer(width, height);
		
		startNanoTime = System.nanoTime();
		setGameState(GameState.WAITING);
		
		
		
		new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				
				double runtime = (now - startNanoTime) / 1000000000.0;
				double deltatime = runtime - lastframe;
				secondtimer += deltatime;
				lastframe = runtime;
				if(runtime > 3600){
					startNanoTime = System.nanoTime();
				}
				if(gamestate == GameState.WAITING){
					
					//TODO: display ip and ports to connect
					r.renderStartScreen(deltatime, (float) Math.sin(runtime*2), ip, players, isServer);
				}
				else{
					
					//TODO: join, errormessage
					
				}
				
				if(secondtimer > 1){
					secondtimer = 0;
					
					
					//update connected players
					players = netclient.getPlayers();
					
				}
				
			}
		}.start();
		
		
		
		
		
	}
	
	public Renderer getCanvas(){
		return r;
	}
	
}
