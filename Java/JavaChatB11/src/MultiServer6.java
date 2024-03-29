import java.io.*;
import java.net.*;
import java.util.*;

public class MultiServer6 {
	ServerSocket serverSochet = null;
	Socket socket = null;
	Map<String, PrintWriter> clientMap;
	
	public MultiServer6() {
		clientMap = new HashMap<String, PrintWriter>();
		Collections.synchronizedMap(clientMap);
	}
	
	public void init() {
		try {
			serverSochet = new ServerSocket(9999);
			System.out.println("서버가 시작되었습니다.");
			
			while(true) {
				socket = serverSochet.accept();
				System.out.println(socket.getInetAddress() + ":" + socket.getPort());
			
				Thread msr = new MultiServerT(socket);
				msr.start();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {	
				serverSochet.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
		
	public void sendAllMsg(String msg) {
	
		Iterator<String> it = clientMap.keySet().iterator();
		
		while(it.hasNext()) {
			try {
				PrintWriter it_out = (PrintWriter)clientMap.get(it.next());
				it_out.println(msg);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	class MultiServerT extends Thread{
		Socket socket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		
		public MultiServerT(Socket socket) {
			this.socket = socket;
			try {
				out = new PrintWriter(socket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(
											socket.getInputStream()));
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			
			String name = "";
			try {
				name = in.readLine();
				sendAllMsg(name + "님이 입장하셨습니다.");				
				clientMap.put(name, out);
				System.out.println("현재 접속자 수는 " + clientMap.size() + "명 입니다.");

				String s = "";
				while(in != null) {
					s = in.readLine();
					System.out.println(s);

					if(s.equals(name+"=>"+"/list")) 	showList(name);
					else if(s.length()>name.length()+5 && s.substring(name.length()+2, name.length()+2+3).equals("/to"))	
														sendToOne(name, s);
					else								sendAllMsg(s);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				clientMap.remove(name);
				sendAllMsg(name + "님이 퇴장하셨습니다.");
				System.out.println("현재 접속자 수는 " + clientMap.size() + "명 입니다.");
				try {
					in.close();
					out.close();	
					socket.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void showList(String name) {
		
			Set<String> k = clientMap.keySet();
			PrintWriter n_out = (PrintWriter)clientMap.get(name);
			n_out.println("현재 접속자입니다("+ clientMap.size()+"명).");
			//for(String s : k )	n_out.println(s) ;
			n_out.println(k);
	}
	
	public void sendToOne(String nm, String s) {
		
		String[] s1 = s.substring(nm.length()+2).split(" ");
		
		PrintWriter out = (PrintWriter)clientMap.get(s1[1]);
		String s2 = "(귓속말)" + nm + "=>" + s.substring(nm.length()+2+ s1[0].length()+s1[1].length()+2);		
		out.println(s2) ;	
	}
	
	public static void main(String[] args){

		MultiServer6 ms = new MultiServer6();
		ms.init();		
	}	
}
