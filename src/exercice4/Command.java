package exercice4;



import graphicLayer.GRect;
import graphicLayer.GSpace;
import stree.parser.SNode;

public interface Command {
    abstract public void run(Reference ref, SNode methode);

	public class setDim implements Command {
		int x;
		int y;

		public setDim(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public void run(Reference ref, SNode methode) {
			((GSpace) ref.receiver).setSize(x, y);
		}
	}

	public class translate implements Command {
		int x, y;

		public translate(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public void run(Reference ref, SNode methode) {
			((GRect) ref.receiver).setX(x);
            ((GRect) ref.receiver).setY(y);
		}
	}

	public class Sleep implements Command {
		int sleepTime;

		public Sleep(int sleepTime) {
			this.sleepTime = sleepTime;
		}

		@Override
		public void run(Reference ref, SNode methode) {
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
