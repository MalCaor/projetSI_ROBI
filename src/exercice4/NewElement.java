package exercice4;

import stree.parser.SNode;
import graphicLayer.*;

class NewElement implements Command {

	//creation d'un nouvel element
	public Reference run(Reference reference, SNode method) {
		
		switch (method.get(0).contents()){
			case "rect.class":
				Reference refRect = new Reference(new GRect());

				refRect.addCommand("setColor", new SetColor());
				refRect.addCommand("translate", new Translate());
				refRect.addCommand("setDim", new SetDim());

				Environment.addReference(method.get(method.size()-1).contents(), refRect);
				return refRect;

			case "oval.class":
				Reference refOval = new Reference(new GOval());

				refOval.addCommand("setColor", new SetColor());
				refOval.addCommand("translate", new Translate());
				refOval.addCommand("setDim", new SetDim());

				Environment.addReference(method.get(method.size()-1).contents(), refOval);
				return refOval;

			case "image.class":
				Reference refImage = new NewImage().run(reference, method);
				refImage.addCommand("setColor", new SetColor());
				Environment.addReference(method.get(method.size()-1).contents(),refImage);
				return refImage;

			case "label.class":
				Reference refLabel = new NewString().run(reference, method);
				refLabel.addCommand("setColor", new SetColor());
				Environment.addReference(method.get(method.size()-1).contents(),refLabel);
				return refLabel;
		}

		return null;
	}
}
