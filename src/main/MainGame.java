package main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainGame extends Application{

	
	//init vars
	double lastframe, secondtimer;
	private Canvas canvas;
	private long startNanoTime;
	
	public static void main(String[] args){
		launch(args);
	}
	
	@Override
	public void start(Stage window) throws Exception {
		window.setTitle("Tron");
		
		Group root = new Group();
		Scene s = new Scene(root);
		BorderPane launcher = new BorderPane();
		
		
		
		Button launch = new Button("Launch");
		launch.setPrefSize(300, 200);
		
		
		launcher.setCenter(launch);
		
		
		
		root.getChildren().add(launcher);
		//init
		window.setScene(s);
		
		window.show();
	}
	
	private void startGame(boolean fullscreen, int players, int enemys){
		
	}

}
