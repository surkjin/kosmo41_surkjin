import java.io.*;
import java.net.*;

public class Receiver3 extends Thread{
	Socket socket;
	BufferedReader in = null;
	
	public Receiver3(Socket socket) {
		this.socket = socket;
		
		try {
			in = new BufferedReader(new InputStreamReader(
					this.socket.getInputStream()));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while (in != null) {
			try {
				System.out.println("Thread Receive: " + in.readLine());
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		try {
			in.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
