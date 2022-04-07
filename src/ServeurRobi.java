import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;

import exercice3.Exercice3_0;
import graphicLayer.GRect;
import graphicLayer.GSpace;
import stree.parser.SNode;
import stree.parser.SParser;
import tools.Tools;

public class ServeurRobi {
	static GSpace space = new GSpace("ROBIC", new Dimension(200, 100));
	static GRect robi = new GRect();
	
    public static void main(String[] args) throws Exception {
        new ServeurRobi();
    }
	public ServeurRobi() throws Exception{
		int indic=0;
		Exercice3_0 commandeInter=new Exercice3_0();
		
		////////////////PASSAGE PARTIE RECEVOIR MESSAGE///////     
		ServerSocket serv=new ServerSocket(8000,1);
		Socket socket = serv.accept();
		BufferedReader br = new BufferedReader(new InputStreamReader( socket.getInputStream() ));
		System.out.println("Serveur lancé!!");
		/*
		space.addElement(robi);
		space.open();
		*/
		String adrr=null;
		String res=null;
		while(adrr==null)adrr=br.readLine();
		while(res==null)res=br.readLine();
		Message ipRecu=new Message();
		ipRecu=ipRecu.fromJson(adrr);
		Message Rec=new Message();
		Rec=Rec.fromJson(res);
		commandeInter.script=Rec.getMess();
		Boolean variable=true;
		while(variable) {
			indic++;
			commandeInter.runScript();
			socket.close();
			envRecu(ipRecu.getMess(),commandeInter.script);
			socket= serv.accept();
			//cliSock=new Socket("localhost",8000);
			br = new BufferedReader(new InputStreamReader( socket.getInputStream() ));
			//adrr=null;
			//res=null;
			System.out.println("3");
		    adrr=null;
		 	res=null;
			while(adrr==null)adrr=br.readLine();
			while(res==null)res=br.readLine();
			ipRecu=new Message();
			ipRecu=ipRecu.fromJson(adrr);
			//envRecu(ipRecu.getMess(),script);			
			
			Rec=Rec.fromJson(res);
			commandeInter.script="";
			commandeInter.script=Rec.getMess();
			System.out.println("5");
		}
		
		socket.close();
		
		System.out.println("Le programme est fini");
	}
	
	public void envRecu(String ip,String commande) throws Exception, IOException {
		////////////////PASSAGE PARTIE ENVOI MESSAGE///////	
		Socket cliSock=new Socket(ip,8001);
		PrintStream ps = new PrintStream(cliSock.getOutputStream());
		ps.println("La commande "+commande+" a bien ete executé");
		cliSock.close();
	}
	
}

