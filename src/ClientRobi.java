import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import client_robi.Interface;

public class ClientRobi{
	public static void main(String[] args) throws Exception {
	////////////////PASSAGE PARTIE ENVOI MESSAGE///////
	String accRecep="";
	Interface recVal=new Interface();
	Thread t=new Thread(new Runnable() {public void run() {
		try {
			recVal.main(args);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}}});
	t.start();
	int indic=0;
	while(indic<3) {
		Socket cliSock=new Socket("localhost",8000);
		System.out.println("connexion validée");
		PrintStream ps = new PrintStream(cliSock.getOutputStream());		
		recVal.s=null;
		String s=recVal.s;
		while(recVal.s==null) {
				Thread.sleep(1000);
		}
		s=recVal.s.replace('\n',' ');
		Message mess2=new Message("String",cliSock.getLocalAddress().toString().substring(1));
		System.out.println(s);
		Message mess=new Message("SNode",s);
		ps.println(mess2.toJson(mess2));
		ps.println(mess.toJson(mess));
		cliSock.close();
		////////////////PASSAGE PARTIE RECEVOIR MESSAGE///////
		ServerSocket serv=new ServerSocket();
		serv.setReuseAddress(true);
		serv.bind(new InetSocketAddress(8001));
		Socket socket = serv.accept();
		BufferedReader br = new BufferedReader(new InputStreamReader( socket.getInputStream() ));
		accRecep+=br.readLine()+'\n';
		recVal.setVal(accRecep);
		socket.close();	
		serv.close();
		indic++;
	}
	recVal.stopInter();
	recVal.stop();
	System.out.println("Le programme est fini");
		 
	}



}



