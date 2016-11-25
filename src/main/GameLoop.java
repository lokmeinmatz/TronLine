package main;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import networking.Client;

public class GameLoop extends Canvas{

	private String playername;
	private Client netclient;
	private int[] color;
	
	public GameLoop(String playername, Client netclient, int[] color, boolean isServer, int width, int height) {
		this.playername = playername;
		this.netclient = netclient;
		this.color = color;
		GraphicsContext gc = this.getGraphicsContext2D();
		if(isServer){
			
			//TODO: display ip and ports to connect
			
		}
		else{
			
			//TODO: join, errormessage
			
		}
		
		
		
		
	}
	
}
