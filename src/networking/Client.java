package networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {

	private int port;
	private String hostIP, playername;
	private InetAddress hostaddress;
	private DatagramSocket socket;
	
	
	public Client(String host, int port, String playername){
		this.port = port;
		this.hostIP = host;
		this.playername = playername;
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
	
	
	
}
