package exercice3;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import graphicLayer.GRect;
import graphicLayer.GSpace;
import stree.parser.SNode;
import stree.parser.SParser;
import tools.Tools;

public class Exercice3_0 {
	GSpace space = new GSpace("Exercice 3", new Dimension(200, 100));
	GRect robi = new GRect();
	String script = "" +
	"   (space setColor black) " +
	"   (robi setColor yellow)" +
	"   (space sleep 1000)" +
	"   (space setColor white)\n" + 
	"   (space sleep 1000)" +
	"	(robi setColor red) \n" + 
	"   (space sleep 1000)" +
	"	(robi translate 100 0)\n" + 
	"	(space sleep 1000)\n" + 
	"	(robi translate 0 50)\n" + 
	"	(space sleep 1000)\n" + 
	"	(robi translate -100 0)\n" + 
	"	(space sleep 1000)\n" + 
	"	(robi translate 0 -40)";

	public Exercice3_0() {
		space.addElement(robi);
		space.open();
		this.runScript();
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

	public static void main(String[] args) {
		new Exercice3_0();
	}

	public interface Command {
		abstract public void run();
	}

	//change la couleur du space
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

	//change la couleur du Robi
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

	//deplace le Robi
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

	//fait attendre le Thread
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