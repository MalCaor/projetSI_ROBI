package graphicLayer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Point2D;

public class GCubicCurve extends GElement {
	CubicCurve2D curve = new CubicCurve2D.Double();
	Stroke stroke;
	
	public GCubicCurve(Point from, Point ctrl1, Point ctrl2, Point to) {
		curve.setCurve(from.x, from.y, ctrl1.x, ctrl1.y, ctrl2.x, ctrl2.y, to.x, to.y);
		stroke = new BasicStroke();
	}
	
	public void translate(Point gap) {
		Point2D from = curve.getP1();
		Point2D to = curve.getP2();
		Point2D ctrl1 = curve.getCtrlP1();
		Point2D ctrl2 = curve.getCtrlP1();
		from.setLocation(from.getX()+ gap.getX(), from.getY()+ gap.getY());
		to.setLocation(to.getX()+ gap.getX(), to.getY()+ gap.getY());
		ctrl1.setLocation(ctrl1.getX()+ gap.getX(), ctrl1.getY()+ gap.getY());
		ctrl2.setLocation(ctrl2.getX()+ gap.getX(), ctrl2.getY()+ gap.getY());
		repaint();
	}
	
	@Override
	public void draw(Graphics2D g) {
		Stroke previousStroke = g.getStroke();
		Color previousColor = g.getColor();

		g.setStroke(stroke);
		g.setColor(color);
		g.draw(curve);

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

}
