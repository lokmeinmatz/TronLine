package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import networking.Client;
import networking.Server;

public class MainGame extends Application{

	
	//init vars
	double lastframe, secondtimer;
	private GameLoop gameloop;
	private Stage window;
	
	public static void main(String[] args){
		launch(args);
	}
	
	@Override
	public void start(Stage window) throws Exception {
		this.window = window;
		window.setTitle("Tron");
		
		Group root = new Group();
		Scene s = new Scene(root);
		BorderPane launcher = new BorderPane();
		VBox settingsbox = new VBox();
		
		//Fullscreen checkbox
		CheckBox fullscreencheckbox = new CheckBox("play Game as FULLSCREEN");
		settingsbox.getChildren().add(fullscreencheckbox);
		
		TextField playername = new TextField("Playername");
		
		
		Slider playersslider = new Slider(1, 4, 1);
		playersslider.setShowTickLabels(true);
		playersslider.setMajorTickUnit(1);
		playersslider.setMinorTickCount(0);
		playersslider.setSnapToTicks(true);
		Label playerssliderlabel = new Label("Number of Players");
		
		TextField Ipinput = new TextField();
		Ipinput.setPromptText("IP of host, e.g. 192.645.24.1");
		
		TextField portInput = new TextField();
		portInput.setPromptText("Port of host, e.g. 8192");
		
		
		
		CheckBox runasServer = new CheckBox("Run as Server");
		runasServer.setOnAction(e -> {
			if(runasServer.selectedProperty().get()){
				Ipinput.setDisable(true);
				portInput.setDisable(true);
			}
			else{
				Ipinput.setDisable(false);
				portInput.setDisable(false);
			}
		});
		
		
		
		
		
		settingsbox.getChildren().addAll(playername, playerssliderlabel, playersslider, new Separator(), 
				runasServer, new Separator(), Ipinput, portInput);
		
		for(Node n:settingsbox.getChildren()){
			VBox.setMargin(n, new Insets(10));
		}
		
		launcher.setLeft(settingsbox);
		
		
		Button launch = new Button("Launch");
		launch.setPrefSize(300, 200);
		launch.setOnAction(e -> {
			if(playername.getText().length() > 4){
				if(runasServer.selectedProperty().get()){
					startGame(fullscreencheckbox.selectedProperty().get(), (int) playersslider.getValue(), 0, 
							true, "localhost", 8192, playername.getText());
				}
				else{
					startGame(fullscreencheckbox.selectedProperty().get(), (int) playersslider.getValue(), 0, 
							false, Ipinput.getText(), Integer.parseInt(portInput.getText()), playername.getText());
				}
				
			}
			
		});
		
		launcher.setCenter(launch);
		
		
		
		root.getChildren().add(launcher);
		//init
		window.setScene(s);
		
		window.show();
	}
	
	private void startGame(boolean fullscreen, int players, int enemys, boolean server, String connectTo, int port, String playername){
		System.out.println("Starting game with fullscreen: "+fullscreen+", Players: "+players+", Enemies: "+enemys);
		Client client;
		String IPasSTR = " ERROR-NO SERVER EXISTING ";
		window.setOnCloseRequest(event -> {
			System.exit(0);
		});
		if(server){
			Server s = new Server(8192);
			s.start();
			client = new Client("localhost", 8192, playername);
			
			IPasSTR = s.getAdress();
			System.out.println(s.getAdress());
		}
		else{
			client = new Client(connectTo, port, playername);
			IPasSTR = connectTo+":"+String.valueOf(port);
		}
		int WIDTH = 1920, HEIGHT = 1080;
		
		if(fullscreen){
			Rectangle2D screenbounds = Screen.getPrimary().getVisualBounds();
			WIDTH = (int) screenbounds.getWidth();
			HEIGHT = (int) screenbounds.getHeight();
		}
		
		if(client.connect()){
			
			GameLoop gameloop = new GameLoop(playername, client, new int[] {123, 123, 123}, server, WIDTH, HEIGHT, IPasSTR);
			BorderPane root = new BorderPane(gameloop.getCanvas());
			Scene gamescene = new Scene(root, WIDTH, HEIGHT);
			window.setScene(gamescene);
			window.setFullScreen(fullscreen);
			window.centerOnScreen();
			System.out.println("Started game");
		}
		else{
			System.out.println("Error while connecting");
		}
	}

}
