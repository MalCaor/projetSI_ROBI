package graphicLayer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

public abstract class GElement  {
	protected GContainer container;
	protected Color color = defaultColor();

	abstract public void draw(Graphics2D g);	
		
	public GElement() { }
	
	public Color defaultColor() {
		return Color.blue;
	}
	
	abstract public void translate(Point gap);

	public GContainer getContainer() {
		return container;
	}
	
	public void setContainer(GContainer aContainer) {
		container = aContainer;
	}
		
	public void repaint () {
		if (container == null) return;
		container.repaint();
	}

	public void setColor (Color c) {
		color = c;
		repaint();		
	}
	
	public void dispatchMouseClicked(MouseEvent e) {}
	public void dispatchMousePressed(MouseEvent e) {}
	public void dispatchMouseReleased(MouseEvent e) {}
	public void dispatchMouseEntered(MouseEvent e) {}
	public void dispatchMouseExited(MouseEvent e) {}	

}
