import java.io.*;
import java.util.*;
import java.net.*;

class chatClient implements Runnable{

	Socket s;
	DataInputStream dis;
	DataOutputStream dos;
	String msg;
	Scanner in;
	Thread th;

	chatClient() throws Exception{
		s= new Socket("Your Server Ip",8080);
		dos = new DataOutputStream(s.getOutputStream());
		dis = new DataInputStream(s.getInputStream());
		in = new Scanner(System.in);
		th = new Thread(this);

		th.start(); //this will start new thread in which run will keep on executing
		//to read the input and we will still be on our main thread.

		while(true){
			String umsg = dis.readUTF();
			System.out.println("Server: "+umsg);
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
			new chatClient();
		}catch(Exception e){
			//System.out.println(e.showMessage());
		}
	}


}
