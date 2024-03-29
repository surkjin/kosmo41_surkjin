import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MultiClient {

	public static void main(String[] args) throws UnknownHostException, IOException {

		System.out.println("이름을 입력해 주세요.");
		Scanner s = new Scanner(System.in);
		String name = s.nextLine();
		PrintWriter out = null;
		BufferedReader in = null;
					
		try {
			String ServerIP = "localhost";
			Socket socket = new Socket(args.length==0 ? ServerIP : args[0], 9999);
			System.out.println("서버와 연결되었습니다.");
				
			Thread receiver = new Receiver(socket);
			receiver.start();
			
			Thread sender = new Sender(socket, name);
			sender.start();
				
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}

