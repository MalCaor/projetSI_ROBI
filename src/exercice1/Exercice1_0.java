package exercice1;

import java.awt.Dimension;
import graphicLayer.GRect;
import graphicLayer.GSpace;

public class Exercice1_0 {
	GSpace space = new GSpace("Exercice 1", new Dimension(200, 150));
	GRect robi = new GRect();

	public Exercice1_0() {
		space.addElement(robi);
		space.open();
		int posX = robi.getX();
		int posY = robi.getY();

		
		boolean retourX = false;
		boolean retourY = false;
		while(true){
			if(posX < space.getWidth()-robi.getWidth() && !retourX && !retourY){
				retourY = false;
				robi.setX(posX+1);
				posX = robi.getX();
			}else{
				retourX = true;
				if(posY < space.getHeight()-robi.getHeight() && !retourY && retourX){
					robi.setY(posY+1);
					posY = robi.getY();
				}else{
					retourY = true;
					if(posX>0 && retourX){
						robi.setX(posX-1);
						posX = robi.getX();
					}else if(posY > 0 && retourY){
						retourX = false;
						robi.setY(posY-1);
						posY = robi.getY();
					}else{
						retourY = false;
					}
				}
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new Exercice1_0();
	}

}