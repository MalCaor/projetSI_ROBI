package exercice5.examples;

import stree.parser.SNode;

import java.lang.reflect.InvocationTargetException;

import graphicLayer.*;

class NewElement implements Command {
	public Reference run(Reference reference, SNode method) {
		//try {
			/*@SuppressWarnings("unchecked")
			GElement e = ((Class<GElement>) reference.getReceiver()).getDeclaredConstructor().newInstance();
			Reference ref = new Reference(e);
			ref.addCommand("setColor", new SetColor());
			ref.addCommand("translate", new Translate());
			ref.addCommand("setDim", new SetDim());*/

			switch (method.get(0).contents()){
				case "Rect":
					Reference refRect = new Reference(new GRect());

					refRect.addCommand("setColor", new SetColor());
					refRect.addCommand("translate", new Translate());
					refRect.addCommand("setDim", new SetDim());

					Environment.addReference(method.get(method.size()-1).contents(), refRect);
					return refRect;

				case "Oval":
					Reference refOval = new Reference(new GOval());

					refOval.addCommand("setColor", new SetColor());
					refOval.addCommand("translate", new Translate());
					refOval.addCommand("setDim", new SetDim());

					Environment.addReference(method.get(method.size()-1).contents(), refOval);
					return refOval;

				case "Image":
					Reference refImage = new NewImage().run(reference, method);
					refImage.addCommand("setColor", new SetColor());
					Environment.addReference(method.get(method.size()-1).contents(),refImage);
					return refImage;

				case "Label":
					Reference refLabel = new NewString().run(reference, method);
					refLabel.addCommand("setColor", new SetColor());
					Environment.addReference(method.get(method.size()-1).contents(),refLabel);
					return refLabel;

				case "script":
					System.out.println("Creation Script");
					Reference refScript = new Reference(null);
					Environment.addReference(method.get(method.size()-1).contents(),refScript);
					return refScript;
			}

			//return null;
		/*} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}*/
		return null;
	}
}
