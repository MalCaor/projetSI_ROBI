import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import exercice3.Exercice3_0;
import exercice3.Exercice3_0.Command;
import exercice3.Exercice3_0.RobiChangeColor;
import exercice3.Exercice3_0.RobiTranslate;
import exercice3.Exercice3_0.Sleep;
import exercice3.Exercice3_0.SpaceChangeColor;
import graphicLayer.GRect;
import graphicLayer.GSpace;
import stree.parser.SNode;
import stree.parser.SParser;
import tools.Tools;

public class ServeurRobi {
	static GSpace space = new GSpace("ROBIC", new Dimension(200, 100));
	static GRect robi = new GRect();
	static String script;
	
    public static void main(String[] args) throws Exception {
        new ServeurRobi();
    }
	public ServeurRobi() throws Exception{
		int indic=0;
		////////////////PASSAGE PARTIE RECEVOIR MESSAGE///////     
		ServerSocket serv=new ServerSocket(8000,1);
		Socket socket = serv.accept();
		BufferedReader br = new BufferedReader(new InputStreamReader( socket.getInputStream() ));
		System.out.println("Serveur lancé!!");
		
		space.addElement(robi);
		space.open();
		String adrr=br.readLine();
		String res=br.readLine();
		Message ipRecu=new Message();
		ipRecu=ipRecu.fromJson(adrr);
		Message Rec=new Message();
		Rec=Rec.fromJson(res);
		script=Rec.getMess();
		while(indic<10) {
			indic++;
			this.runScript();
			socket.close();
			envRecu(ipRecu.getMess(),script);
			
			socket= serv.accept();
			br = new BufferedReader(new InputStreamReader( socket.getInputStream() ));
			adrr=null;
			res=null;
			while(adrr==null)adrr=br.readLine();
			while(res==null)res=br.readLine();
			ipRecu=new Message();
			ipRecu=ipRecu.fromJson(adrr);
			//envRecu(ipRecu.getMess(),script);			
			
			Rec=Rec.fromJson(res);
			script="";
			script=Rec.getMess();

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
	
	private void runScript() {
		SParser<SNode> parser = new SParser<>();
		List<SNode> rootNodes = null;
		try {
			rootNodes = parser.parse(script);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Iterator<SNode> itor = rootNodes.iterator();
		while (itor.hasNext()) {
			this.run(itor.next());
		}
	}

	private void run(SNode expr) {
		Command cmd = getCommandFromExpr(expr);
		if (cmd == null)
			throw new Error("unable to get command for: " + expr);
		cmd.run();
	}

	Command getCommandFromExpr(SNode expr) {
		String objet = expr.get(0).contents();
		String commande = expr.get(1).contents();

		switch(objet){
			case "space":
				switch(commande){
					case "sleep":
						return new Sleep(Integer.parseInt(expr.get(2).contents()));

					case "setColor":
						return new SpaceChangeColor(Tools.getColorByName(expr.get(2).contents()));
					
					default:
						break;
				}
				break;

			case "robi":
				switch(commande){
					case "sleep":
						return new Sleep(Integer.parseInt(expr.get(2).contents()));
					case "translate":
						return new RobiTranslate(Integer.parseInt(expr.get(2).contents()),Integer.parseInt(expr.get(3).contents()));
					case "setColor":
						return new RobiChangeColor(Tools.getColorByName(expr.get(2).contents()));
				}
				break;
		}

		return null;
	}

	public interface Command {
		abstract public void run();
	}

	public class SpaceChangeColor implements Command {
		Color newColor;

		public SpaceChangeColor(Color newColor) {
			this.newColor = newColor;
		}

		@Override
		public void run() {
			space.setColor(newColor);
		}

	}

	public class RobiChangeColor implements Command {
		Color newColor;

		public RobiChangeColor(Color newColor) {
			this.newColor = newColor;
		}

		@Override
		public void run() {
			robi.setColor(newColor);
		}
	}

	public class RobiTranslate implements Command {
		int x, y;

		public RobiTranslate(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public void run() {
			robi.setX(x);
			robi.setY(y);
		}
	}

	public class Sleep implements Command {
		int sleepTime;

		public Sleep(int sleepTime) {
			this.sleepTime = sleepTime;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

