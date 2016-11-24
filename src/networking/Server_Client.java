package networking;

import java.net.InetAddress;

public class Server_Client {

	private String playername;
	private InetAddress playerInetAdress;
	private int port;
	private int[] colors;
	
	
	public Server_Client(String playername, InetAddress playerInetAdress, int port, int[] colors) {
		super();
		this.playername = playername;
		this.playerInetAdress = playerInetAdress;
		this.port = port;
		this.colors = colors;
	}


	public String getPlayername() {
		return playername;
	}


	public void setPlayername(String playername) {
		this.playername = playername;
	}


	public InetAddress getPlayerInetAdress() {
		return playerInetAdress;
	}


	public void setPlayerInetAdress(InetAddress playerInetAdress) {
		this.playerInetAdress = playerInetAdress;
	}


	public int getPort() {
		return port;
	}


	public void setPort(int port) {
		this.port = port;
	}


	public int[] getColors() {
		return colors;
	}


	public void setColors(int[] colors) {
		this.colors = colors;
	}
	
	//private 
	
	
}
