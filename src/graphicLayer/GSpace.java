package graphicLayer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GSpace extends JPanel implements GContainer, KeyListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private List<GElement> elements = new LinkedList<GElement>();
	private List<KeyListener> keyListeners = new LinkedList<KeyListener>();

	String name = "";

	public GSpace(String name, Dimension dim) {
		this.name = name;
		super.addKeyListener(this);
		super.addMouseListener(this);
		this.setPreferredSize(dim);
	}

	public List<GElement> contents() {
		return elements;
	}

	public void changeWindowSize(Dimension dim) {
		this.setPreferredSize(dim);
		JFrame frame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this);
		frame.pack();
	}
	
	public void open() {
		JFrame frame = new JFrame(name);
		WindowAdapter wa = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		frame.addWindowListener(wa);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
		requestFocus();
	}

	public void addElement(GElement d) {
		elements.add(d);
		d.setContainer(this);
	}

	public void removeElement(GElement d) {
		elements.remove(d);
		d.setContainer(null);
	}

	public void setColor(Color c) {
		setBackground(c);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		GElement [] elems = (GElement[]) elements.toArray(new GElement[elements.size()]);
		for (int n = 0; n < elems.length; n++) {
			elems[n].draw(g2d);
		}
	}

	public void addKeyListener(KeyListener k) {
		keyListeners.add(k);
	}
	
	public void clear() {
		for (Iterator<GElement> iter = elements.iterator(); iter.hasNext();) {
			iter.next().setContainer(null);
		}
		elements.clear();
		this.repaint();
	}

	public void keyTyped(KeyEvent e) {
		for (Iterator<KeyListener> iter = keyListeners.iterator(); iter.hasNext();) {
			KeyListener keyListener = iter.next();
			keyListener.keyTyped(e);
		}
	}

	public void keyPressed(KeyEvent e) {
		for (Iterator<KeyListener> iter = keyListeners.iterator(); iter.hasNext();) {
			KeyListener keyListener = iter.next();
			keyListener.keyPressed(e);
		}
	}

	public void keyReleased(KeyEvent e) {
		for (Iterator<KeyListener> iter = keyListeners.iterator(); iter.hasNext();) {
			KeyListener keyListener = iter.next();
			keyListener.keyReleased(e);
		}
	}

	public void mouseClicked(MouseEvent e) {
		for (Iterator<GElement> iter = elements.iterator(); iter.hasNext();) {
			GElement element = iter.next();
			element.dispatchMouseClicked(e);
		}
	}

	public void mousePressed(MouseEvent e) {
		for (Iterator<GElement> iter = elements.iterator(); iter.hasNext();) {
			GElement element = iter.next();
			element.dispatchMousePressed(e);
		}
	}

	public void mouseReleased(MouseEvent e) {
		for (Iterator<GElement> iter = elements.iterator(); iter.hasNext();) {
			GElement element = iter.next();
			element.dispatchMouseReleased(e);
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public Point getGlobalPosition() {
		return null;
	}

	public GElement [] getRawContents() {
		return elements.toArray(new GElement[elements.size()]);
	}

}