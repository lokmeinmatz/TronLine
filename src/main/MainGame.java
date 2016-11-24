package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import networking.Client;
import networking.Server;

public class MainGame extends Application{

	
	//init vars
	double lastframe, secondtimer;
	private GameLoop gameloop;
	
	public static void main(String[] args){
		launch(args);
	}
	
	@Override
	public void start(Stage window) throws Exception {
		window.setTitle("Tron");
		
		Group root = new Group();
		Scene s = new Scene(root);
		BorderPane launcher = new BorderPane();
		VBox settingsbox = new VBox();
		
		//Fullscreen checkbox
		CheckBox fullscreencheckbox = new CheckBox("play Game as FULLSCREEN");
		settingsbox.getChildren().add(fullscreencheckbox);
		
		Slider playersslider = new Slider(1, 4, 1);
		playersslider.setShowTickLabels(true);
		playersslider.setMajorTickUnit(1);
		playersslider.setMinorTickCount(0);
		playersslider.setSnapToTicks(true);
		Label playerssliderlabel = new Label("Number of Players");
		
		CheckBox runasServer = new CheckBox("Run as Server");
		settingsbox.getChildren().addAll(playerssliderlabel, playersslider, new Separator(), runasServer);
		
		for(Node n:settingsbox.getChildren()){
			settingsbox.setMargin(n, new Insets(10));
		}
		
		launcher.setLeft(settingsbox);
		
		
		Button launch = new Button("Launch");
		launch.setPrefSize(300, 200);
		launch.setOnAction(e -> {
			startGame(fullscreencheckbox.selectedProperty().get(), (int) playersslider.getValue(), 0);
		});
		
		launcher.setCenter(launch);
		
		
		
		root.getChildren().add(launcher);
		//init
		window.setScene(s);
		
		window.show();
	}
	
	private void startGame(boolean fullscreen, int players, int enemys){
		System.out.println("Starting game with fullscreen: "+fullscreen+", Players: "+players+", Enemies: "+enemys);
		Server s = new Server(8192);
		s.start();
		Client client = new Client("localhost", 8192, "lokmeinmatz");
		client.connect();
	}

}
