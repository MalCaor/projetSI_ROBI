package graphicLayer;
import java.awt.Point;

public interface GContainer {
	void repaint();
	Point getGlobalPosition();
	GElement [] getRawContents();
	void addElement(GElement m);
	void removeElement(GElement m);
	void clear();
}
