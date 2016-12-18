package networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import main.Player;

public class Client {

	private int port;
	private String hostIP, playername;
	private InetAddress hostaddress;
	private DatagramSocket socket;
	private final int MAX_PACKET_SIZE = 1024;
	private byte[] recievedDataBuffer = new byte[MAX_PACKET_SIZE * 10];
	private boolean listening = false;
	private Thread ServerListener;
	private List<Player> netplayers;
	
	public Client(String host, int port, String playername){
		this.port = port;
		this.hostIP = host;
		this.playername = playername;
		netplayers = new ArrayList<>();
	}
	
	
	public boolean connect(){
		try {
			hostaddress = InetAddress.getByName(hostIP);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
			listening = true;
			ServerListener = new Thread(() -> {
				
				listen();
				
			}, "serverLISTENER");
			ServerListener.start();
			return false;
			
		}
		
	SendConnectionPacket();
	return true;	
	}
	
	private void SendConnectionPacket(){
		byte[] data = ("LOGIN§"+playername+"§"+"192,133,123").getBytes();
		send(data);
	}
	
	
	public void send(byte[] data){
		assert(socket.isConnected());
		DatagramPacket packet = new DatagramPacket(data, data.length, hostaddress, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void listen(){
		while(listening){
			DatagramPacket packet = new DatagramPacket(recievedDataBuffer, MAX_PACKET_SIZE);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			process(packet);
			
		}
	}
	
	
	private void process(DatagramPacket packet){
		byte[] data = packet.getData();
		if(new String(data, 0, 5).equals("NWPLR")){
			String[] playerinfo = new String(data).split("§");
			String playername = playerinfo[1];
			int[] pcolor = new int[]{
					Integer.parseInt(playerinfo[2]),
					Integer.parseInt(playerinfo[3]),
					Integer.parseInt(playerinfo[4])
			};
			
			Player p = new Player(pcolor, playername, null, null, 0);
			netplayers.add(p);
			
		}
		else{
			String[] logininfo = new String(data).split("§");
			
			
			
			switch (logininfo[0]) {
			case "GDATA":
				System.out.println(logininfo[1]);
				break;

			default:
				break;
			}
		}
	}
	
	public List<Player> getPlayers(){
		return netplayers;
	}
	
}
