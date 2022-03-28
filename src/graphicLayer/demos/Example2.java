
package graphicLayer.demos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import graphicLayer.GBounded;
import graphicLayer.GSpace;

public class Example2  {

	public Example2() {
		GSpace w = new GSpace("Un essai", new Dimension(800, 600));
		GBounded container = new GBounded();
		container.setColor(Color.white);
		container.setDimension(new Dimension(400,300));
		w.addElement(container);
		
		GBounded subContainer = new GBounded();
		subContainer.setColor(Color.red);
		subContainer.setPosition(new Point(10,10));
		subContainer.setDimension(new Dimension(100,50));
		container.addElement(subContainer);
		
		GBounded subsubContainer = new GBounded();
		subsubContainer.setColor(Color.black);
		subsubContainer.setPosition(new Point(10,5));
		subContainer.setDimension(new Dimension(80,30));
		subContainer.addElement(subsubContainer);

		w.open();
	}

	
	public static void main(String[] args) {
		new Example2();
	}

}
