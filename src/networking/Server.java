package networking;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {

	private int port;
	private Thread NetListener;
	private boolean listening = false;
	private DatagramSocket socket;
	
	public Server(int port){
		this.port = port;
	}

	public void start(){
		listening = true;
		
		try {
			socket = new DatagramSocket(getPort());
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		NetListener = new Thread(() -> {
			
			listen();
			
		});
		NetListener.start();
	}
	
	private void listen(){
		while(listening){
			
		}
	}
	
	
	private void process(DatagramPacket packet){
		
	}
	
	public void send(byte[] data, InetAddress address, int port){
		assert(socket.isConnected());
		
		DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
		socket.send(packet);
	}
	
	
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	
	
}
