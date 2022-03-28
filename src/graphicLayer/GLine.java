package graphicLayer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

public class GLine extends GElement {
	Point from;
	Point to;
	Stroke stroke;
	
	public GLine(Point from, Point to) {
		this.from = from;
		this.to = to;
		stroke = new BasicStroke();
	}
	
	public GLine(int fromX, int fromY, int toX, int toY) {
		this(new Point(fromX, fromY), new Point(toX, toY));
	}
	
	@Override
	public void draw(Graphics2D g) {
		Stroke previousStroke = g.getStroke();
		Color previousColor = g.getColor();

		g.setStroke(stroke);
		g.setColor(color);
		g.drawLine(from.x, from.y, to.x, to.y);

		g.setColor(previousColor);
		g.setStroke(previousStroke);
	}

	public void setStroke(Stroke stroke) {
		this.stroke = stroke;
		repaint();
	}
	
	public void setWidth(float w) {
		setStroke(new BasicStroke(w));
	}

	public void translate(Point gap) {
		from.translate(gap.x, gap.y);
		to.translate(gap.x, gap.y);
		repaint();
	}

}
