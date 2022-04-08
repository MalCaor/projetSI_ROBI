import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import client_robi.Interface;

public class ClientRobi {
	static String valRetour = null;

	public static void main(String[] args) throws Exception {
		Socket cliSock = null;
		Socket cliSock2 = null;
		Boolean erreur = false;
		String accRecep = "";
		String envoie = "";
		int indic = 0;
		Interface recVal = new Interface();
		// création d'un thread qui s'occupe de la gestion de l'interface graphique
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					recVal.main(args);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
		////////////////////// RENTRE DANS LA BOUCLE ENVOIE DE MESSAGE///////////

		while (indic < 10) { // Le client fait 10 commandes ensuite la connection est coupée

			///////////////////// TRY/CATCH ERREUR CONNECTION REFUSED///////////////
			try {
				cliSock = new Socket("localhost", 8000);
				cliSock2 = new Socket("localhost", 8000);// création d'un second socket inutile pour remplir le backlog
															// du serveur et ainsi bloquer les autres connections
															// entrantes
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
				envoie = recVal.s.replace('\n', ' ');
				//////////////// PASSAGE PARTIE ENVOI MESSAGE SOCKET 1///////
				envoie = recVal.s.replace('\n', ' ');
				Message mess2 = new Message("String", "localhost");
				System.out.println(envoie);
				Message mess = new Message("String", envoie);
				ps.println(mess2.toJson(mess2));
				ps.println(mess.toJson(mess));
				cliSock.close();
				System.out.println("envoie exp");

				//////////////// PASSAGE PARTIE RECEVOIR MESSAGE SOCKET 1///////
				ServerSocket serv = new ServerSocket();
				serv.setReuseAddress(true);
				serv.bind(new InetSocketAddress(8001));
				Socket socket = serv.accept();
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				accRecep += br.readLine() + '\n';
				recVal.setVal(accRecep);
				socket.close();
				serv.close();

				////////////////PASSAGE PARTIE ENVOI MESSAGE SOCKET 2///////
				ps = new PrintStream(cliSock2.getOutputStream());
				envoie = "(space sleep 100)";
				mess2 = new Message("String", "localhost");
				System.out.println(envoie);
				mess = new Message("SNode", envoie);
				ps.println(mess2.toJson(mess2));
				ps.println(mess.toJson(mess));
				cliSock2.close();

				//////////////// PASSAGE PARTIE RECEVOIR MESSAGE SOCKET 2///////
				serv = new ServerSocket();
				serv.setReuseAddress(true);
				serv.bind(new InetSocketAddress(8001));
				socket = serv.accept();
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				// recVal.setVal(accRecep);
				socket.close();
				serv.close();

				indic++;//On incrémente la valeur d'indic pour compter le nombre de commandes envoyés.
			} else {
				Thread.sleep(1000);
				//si la connection a été refusé alors un autre client est déja connecté au serveur on ferme le client.
				recVal.setVal("La connection a été refusé par le serveur une action est deja en cours");
				indic = 10000;//On incrémente a une valeur inatteignable pour ne pas rentrer à nouveau dans la boucle
			}
		}
		Thread.sleep(1000);
		recVal.finishInter();//On ferme l'interface graphique
		System.out.println("Le programme est fini");

	}

}
