package projetsi.exercice1;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;
import graphicLayer.GRect;
import graphicLayer.GSpace;


public class Exercice1_0 {
	GSpace space = new GSpace("Exercice 1", new Dimension(200, 150));
	GRect robi = new GRect();

	Random rand = new Random();

	public Exercice1_0() {
		space.addElement(robi);
		space.open();
		boolean retour = false;
		while (true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			//le robi fait le tour du space, et change de couleur à chaque tour
			if(!retour)
			{
				if(robi.getX() < (space.getWidth()-20)){
					robi.setX(robi.getX()+5);
					continue;
				}
				if(robi.getY() < (space.getHeight()-20)){
					robi.setY(robi.getY()+5);
					continue;
				}
				retour = true;
			} else {
				if(robi.getX() > 0){
					robi.setX(robi.getX()-5);
					continue;
				}
				if(robi.getY() > 0){
					robi.setY(robi.getY()-5);
					continue;
				}
				retour = false;
				// Java 'Color' class takes 3 floats, from 0 to 1.
				float r = rand.nextFloat();
				float g = rand.nextFloat();
				float b = rand.nextFloat();
				Color randomColor = new Color(r, g, b);
				robi.setColor(randomColor);
			}

		}
	}

	public static void main(String[] args) {
		new Exercice1_0();
	}

}