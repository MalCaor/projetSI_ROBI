package graphicLayer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;

public class GRect extends GBounded {
	Color borderColor;
	Stroke stroke;
	boolean withBorder;
	
	public Color defaultBorderColor() {
		return Color.black;
	}
	public Stroke defaultStroke() {
		return new BasicStroke(1);
	}
	
	public GRect() {
		withBorder = true;
		borderColor = defaultBorderColor();
		stroke = defaultStroke();
	}
	
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
		repaint();		
	}
	
	public void setBorderWidth(float w) {
		setStroke(new BasicStroke(w));
	}
	
	public void setStroke(Stroke stroke) {
		this.stroke = stroke;
		repaint();
	}
	
	public void withoutBorder() {
		withBorder = false;
	}
	public void withBorder() {
		withBorder = true;
	}
	
	public void draw(Graphics2D g) {
		Rectangle bounds = this.getBounds();
		Color previousColor = g.getColor();
		Stroke previousStroke = g.getStroke();
		
		g.setColor(color);
		g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
		if (withBorder) {
			g.setColor(borderColor);
			g.setStroke(stroke);
			g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
		}
		
		g.setColor(previousColor);
		g.setStroke(previousStroke);
		
		drawContents(g);
	}

	
}
