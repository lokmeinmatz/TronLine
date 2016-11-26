package graphics;

import java.util.List;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.Player;

public class Renderer extends Canvas{
	
	int WIDTH, HEIGHT; 
	GraphicsContext gc;
	public Renderer(int WIDTH, int HEIGHT){
		this.HEIGHT = HEIGHT;
		this.WIDTH = WIDTH;
		this.setWidth(WIDTH);
		this.setHeight(HEIGHT);
		gc = this.getGraphicsContext2D();
		
		
		
		
		
		
		
		
		
		
	}
	
	public void setUpStartScreen(){
		this.setOnMousePressed(e -> {
			System.out.println(e.getSceneX()+"  "+e.getSceneY());
			Rectangle2D buttonplay = new Rectangle2D(WIDTH/2-100, HEIGHT/1.5, 200, 60);
			if(buttonplay.contains(new Point2D(e.getSceneX(), e.getSceneY()))){
				System.out.println("Starting Game");
			}
		});
	}

	public void renderStartScreen(double deltatime, float blend, String ip, List<Player> players){
		
		//gc.clearRect(0, 0, WIDTH, HEIGHT);
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, WIDTH, HEIGHT);
		gc.setFill(new Color(1, 1, 1, Math.min((Math.max(((blend+1)/2), 0)), 1)  ));
		gc.setFont(new Font(60));
		gc.fillText("Waiting for players", WIDTH/2-200, HEIGHT/2-100);
		gc.setFont(new Font(80));
		gc.setFill(Color.WHITE);
		gc.fillText("IP: "+ip, WIDTH/2-200, HEIGHT/2);
		
		gc.setFill(Color.RED);
		gc.fillRoundRect(WIDTH/2-100, HEIGHT/1.5, 200, 60, 10, 10);
		gc.setFill(Color.WHITE);
		gc.setFont(new Font(30));
		gc.fillText("Start Game", WIDTH/2-80 ,HEIGHT/1.5+40);
		
		
		gc.setFill(Color.DARKGREY);
		gc.fillRect(WIDTH/5, HEIGHT/4, 250, players.size()*60);
		
		gc.setFill(Color.WHITE);
		gc.fillText("Connected Players", WIDTH/5, HEIGHT/4-40);
		
		//render Lobby-Players
		for (int i = 0; i < players.size(); i++){
			
			Player p = players.get(i);
			gc.setFill(new Color(((float)p.getColor()[0])/255, ((float)p.getColor()[1])/255, ((float)p.getColor()[2])/255, 1));
			gc.fillText(p.getPlayername(), WIDTH/5+5, (HEIGHT/4+(i)*60)+50);
			
		}
	}
	
	
	
	
	public int getWIDTH() {
		return WIDTH;
	}

	public void setWIDTH(int wIDTH) {
		WIDTH = wIDTH;
		this.setWidth(WIDTH);
	}

	public int getHEIGHT() {
		return HEIGHT;
	}

	public void setHEIGHT(int hEIGHT) {
		HEIGHT = hEIGHT;
		this.setHeight(HEIGHT);
	}
	
	
	
	
	
	
	
}
