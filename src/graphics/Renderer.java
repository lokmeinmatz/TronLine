package graphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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

	public void renderStartScreen(double deltatime, float blend, String ip){
		
		//gc.clearRect(0, 0, WIDTH, HEIGHT);
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, WIDTH, HEIGHT);
		gc.setFill(new Color(1, 1, 1, Math.min((Math.max(((blend+1)/2), 0)), 1)  ));
		gc.setFont(new Font(40));
		gc.fillText("Waiting for players", WIDTH/2-200, HEIGHT/3);
		gc.setFont(new Font(20));
		gc.fillText("IP: "+ip, WIDTH/2-200, HEIGHT/2);
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