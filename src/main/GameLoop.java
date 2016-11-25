package main;

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
	
	
	private enum GameState{
		LOADING, CONNECTING, WAITING, PLAYING, WINNERSCREEN, RESET
	}
	
	public GameLoop(String playername, Client netclient, int[] color, boolean isServer, int width, int height, String ip) {
		
		gamestate = GameState.LOADING;
		
		this.playername = playername;
		this.netclient = netclient;
		this.dimensions = new int[] {width, height};
		this.color = color;
		r = new Renderer(width, height);
		System.out.println(isServer);
		startNanoTime = System.nanoTime();
		gamestate = GameState.WAITING;
		
		
		
		new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				
				double runtime = (now - startNanoTime) / 1000000000.0;
				double deltatime = runtime - lastframe;
				secondtimer += deltatime;
				lastframe = runtime;
				if(isServer && gamestate == GameState.WAITING){
					
					//TODO: display ip and ports to connect
					r.renderStartScreen(deltatime, (float) Math.sin(runtime*2), ip);
				}
				else{
					
					//TODO: join, errormessage
					
				}
				
			}
		}.start();;
		
		
		
		
		
	}
	
	public Renderer getCanvas(){
		return r;
	}
	
}
