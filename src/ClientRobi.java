import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Random;

import client_robi.Interface;

public class ClientRobi {
	static String valRetour = null;

	public static void main(String[] args) throws Exception {
		int compteur = 0;
		Socket cliSock = null;// =new Socket("localhost",8000);
		Socket cliSock2 = null;
		// SocketAddress receveur=new InetSocketAddress("localhost",8000);
		//////////////// PASSAGE PARTIE ENVOI MESSAGE///////
		Boolean erreur = false;
		String accRecep = "";
		String envoie = "";
		int indic = 0;
		Interface recVal = new Interface();
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					recVal.main(args);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		t.start();
		////////////////////// RENTRE DANS LA BOUCLE ENVOIE DE MESSAGE///////////
		while (indic < 1) {
			compteur = 0;
			///////////////////// TRY/CATCH ERREUR CONNECTION REFUSED///////////////
			try {
				// cliSock.connect(receveur);
				cliSock = new Socket("localhost", 8000);
				cliSock2 = new Socket("localhost", 8000);
			} catch (java.net.ConnectException e) {
				System.out.println("Probleme de connection.");
				erreur = true;
			}
			///////////////////// ADAPTATION CODE PRECEDENT CONSEQUENCE TRY////////
			if (erreur == false) {
				PrintStream ps = new PrintStream(cliSock.getOutputStream());
				recVal.s = null;
				envoie = recVal.s;
				while (recVal.s == null) {
					Thread.sleep(1000);
				}
				//////////////// PASSAGE PARTIE ENVOI MESSAGE///////
				envoie = recVal.s.replace('\n', ' ');
					Message mess2 = new Message("String", "localhost");
					System.out.println(envoie);
					Message mess = new Message("SNode", envoie);
					ps.println(mess2.toJson(mess2));
					ps.println(mess.toJson(mess));
					cliSock.close();
					System.out.println("envoie exp");

					//////////////// PASSAGE PARTIE RECEVOIR MESSAGE///////
					ServerSocket serv = new ServerSocket();
					serv.setReuseAddress(true);
					serv.bind(new InetSocketAddress(8001));
					Socket socket = serv.accept();
					BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					accRecep += br.readLine() + '\n';
					recVal.setVal(accRecep);
					socket.close();
					serv.close();

					ps = new PrintStream(cliSock2.getOutputStream());
					envoie = "(space sleep 100)";
					mess2 = new Message("String", "localhost");
					System.out.println(envoie);
					mess = new Message("SNode", envoie);
					ps.println(mess2.toJson(mess2));
					ps.println(mess.toJson(mess));
					cliSock2.close();
					System.out.println("envoie exp");

					//////////////// PASSAGE PARTIE RECEVOIR MESSAGE///////
					serv = new ServerSocket();
					serv.setReuseAddress(true);
					serv.bind(new InetSocketAddress(8001));
					socket = serv.accept();
					br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					// accRecep+=br.readLine()+'\n';
					// recVal.setVal(accRecep);
					socket.close();
					serv.close();

					indic++;

			} else {
				Thread.sleep(1000);
				recVal.setVal("La connection a été refusé par le serveur une action est deja en cours");
				indic = 10000;
			}
		}
		Thread.sleep(1000);
		recVal.finishInter();
		System.out.println("Le programme est fini");

	}

}
