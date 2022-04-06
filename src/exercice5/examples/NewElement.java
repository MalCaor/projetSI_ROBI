package exercice5.examples;

import stree.parser.SNode;
import graphicLayer.*;

//Creation d'un nouvel Element
class NewElement implements Command {
	public Reference run(Reference reference, SNode method) {
		
		//creation de la reference, ajout des commandes auquelscette reference a acces
		//puis ajout de la reference a la liste des references de l'environnement
		switch (method.get(0).contents()){
			case "Rect":
				Reference refRect = new Reference(new GRect());

				refRect.addCommand("setColor", new SetColor());
				refRect.addCommand("translate", new Translate());
				refRect.addCommand("setDim", new SetDim());
				refRect.addCommand("addChild", new AddChild());
				refRect.addCommand("add", new AddElement());

				Environment.addReference(method.get(method.size()-1).contents(), refRect);
				return refRect;

			case "Oval":
				Reference refOval = new Reference(new GOval());

				refOval.addCommand("setColor", new SetColor());
				refOval.addCommand("translate", new Translate());
				refOval.addCommand("setDim", new SetDim());
				refOval.addCommand("addChild", new AddChild());
				refOval.addCommand("add", new AddElement());

				Environment.addReference(method.get(method.size()-1).contents(), refOval);
				return refOval;

			case "Image":
				Reference refImage = new NewImage().run(reference, method);
				refImage.addCommand("setColor", new SetColor());
				refImage.addCommand("addChild", new AddChild());
				Environment.addReference(method.get(method.size()-1).contents(),refImage);
				return refImage;

			case "Label":
				Reference refLabel = new NewString().run(reference, method);
				refLabel.addCommand("setColor", new SetColor());
				refLabel.addCommand("addChild", new AddChild());
				Environment.addReference(method.get(method.size()-1).contents(),refLabel);
				return refLabel;

			case "script":
				System.out.println("Creation Script");
				Reference refScript = new Reference(null);
				Environment.addReference(method.get(method.size()-1).contents(),refScript);
				return refScript;
		}
			
		return null;
	}
}
