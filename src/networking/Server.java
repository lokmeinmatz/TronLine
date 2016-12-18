package networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Server {

	private int port;
	private Thread NetListener;
	private boolean listening = false;
	private DatagramSocket socket;
	private final int MAX_PACKET_SIZE = 1024;
	private List<Server_Client> clients;
	private byte[] recievedDataBuffer = new byte[MAX_PACKET_SIZE * 10];
	
	public Server(int port){
		this.port = port;
		clients = new ArrayList<>();
	}

	public void start(){
		listening = true;
		System.out.println("Starting server");
		try {
			socket = new DatagramSocket(getPort());
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		NetListener = new Thread(() -> {
			
			listen();
			
		}, "NetLISTENER");
		NetListener.start();
		System.out.println(socket.getLocalPort());
		
	}
	
	
	public String getAdress(){
		try {
			return socket.getInetAddress().getLocalHost().getHostAddress()+"  Port:"+socket.getLocalPort();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "WHUUT";
		
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
		if(new String(data, 0, 5).equals("LOGIN")){
			String[] logininfo = new String(data).split("§");
			System.out.println(logininfo[1]+" joined!");
			
			Server_Client c = new Server_Client(logininfo[1], packet.getAddress(), packet.getPort(), new int[]{111, 111, 111});
			if(!clients.contains(c)){
				for(Server_Client cls:clients){
					String joinstring = "NWPLR§"+c.getPlayername()+"§"+c.getColors()[0]+"$";
					send(joinstring.getBytes(), cls.getPlayerInetAdress(), cls.getPort());
				}
				clients.add(c);
			}
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
	
	public void send(byte[] data, InetAddress address, int port){
		assert(socket.isConnected());
		
		DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void login(){
		
		
		return;
	}
	
	
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	
	
}
