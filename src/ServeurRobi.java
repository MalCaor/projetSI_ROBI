import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import exercice5.examples.*;
import graphicLayer.GRect;
import graphicLayer.GSpace;
import stree.parser.SNode;
import stree.parser.SParser;

public class ServeurRobi {
	static GSpace space = new GSpace("ROBIC", new Dimension(200, 100));
	static GRect robi = new GRect();
	
    public static void main(String[] args) throws Exception {
        new ServeurRobi();
    }
    
	public ServeurRobi() throws Exception{		
		////////////////PASSAGE PARTIE RECEVOIR MESSAGE///////     
		ServerSocket serv=new ServerSocket(8000,1);// On crée un serveur socket avec pour backlog la valeur minimale 
		
		//On initialise les variables qui vont servir pour executer les programmes recus 
		Environment environment=new Environment();
		SParser<SNode> parser=new SParser<>();
		List <SNode> compiled=null;
		new Exercice4_2_0();
		
		Socket socket = serv.accept();
		BufferedReader br = new BufferedReader(new InputStreamReader( socket.getInputStream() ));
		
		String adrr=null;
		String res=null;
		while(adrr==null)adrr=br.readLine();//On recupere l'adresse envoyé par le client
		while(res==null)res=br.readLine();//On recupere le message envoyé par le client
		
		//On convertir ces String en Message pour recuperer leur valeur
		Message ipRecu=new Message();
		ipRecu=ipRecu.fromJson(adrr);
		Message Rec=new Message();
		Rec=Rec.fromJson(res);
		
		try {
			compiled=parser.parse(Rec.getMess());//On converti le String en List de SNode
		}catch(IOException e) {
			e.printStackTrace();
		}	
		
		Iterator <SNode> itor=compiled.iterator();//On initialise l'iterateur
		Boolean variable=true;
		while(variable){
			
			while(itor.hasNext()) {
				new Interpreter().compute(environment, itor.next());//On execute le programme demandé
			}
			
			socket.close();
			envRecu(ipRecu.getMess(),Rec.getMess());
			socket= serv.accept();
			br = new BufferedReader(new InputStreamReader( socket.getInputStream() ));
			
		    adrr=null;
		 	res=null;
			while(adrr==null)adrr=br.readLine();//On attend l'adresse ip du client
			while(res==null)res=br.readLine();  //On attend le message du client
			ipRecu=new Message();
			ipRecu=ipRecu.fromJson(adrr);
			Rec=Rec.fromJson(res);
			try {
				compiled=parser.parse(Rec.getMess());//On converti le String en List de SNode
			}catch(IOException e) {
				e.printStackTrace();
			}
			itor=compiled.iterator();//On reinitialise l'iterateur
			
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

