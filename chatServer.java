import java.io.*;
import java.util.*;
import java.net.*;

class chatServer implements Runnable{

	ServerSocket server;
	Socket s;
	DataInputStream dis;
	DataOutputStream dos;
	String msg;
	Scanner in;
	Thread th;

	chatServer() throws Exception{
		server= new ServerSocket(8080);
		s = server.accept();
		dos = new DataOutputStream(s.getOutputStream());
		dis = new DataInputStream(s.getInputStream());
		in = new Scanner(System.in);
		th = new Thread(this);

		th.start(); //this  will start new thread in which run will keep on executing
		//to read the input and we will still be on our main thread.

		while(true){
			String umsg = dis.readUTF();
			System.out.println("Client: "+umsg);
		}
	}

	
	//for receving input new thread is created
	public void run(){
		while(true){
			try{	
				msg= in.nextLine();
				dos.writeUTF(msg);
			}catch(Exception e){
				//System.out.println(e.showMessage());
			}
		}
	}


	public static void main(String args[]){

		try{
			new chatServer();
		}catch(Exception e){
			//System.out.println(e.showMessage());
		}
	}


}