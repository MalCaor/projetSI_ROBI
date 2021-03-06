package exercice5.examples;

import java.awt.Color;

import graphicLayer.GElement;
import graphicLayer.GSpace;
import stree.parser.SNode;
import tools.Tools;


//change la couleur d'un GElement
public class SetColor implements Command {
    Color newColor;

    public SetColor() {
    }

    @Override
    public Reference run(Reference ref, SNode methode) {
        newColor = Tools.getColorByName(methode.get(2).contents());
        try {
            ((GElement) ref.receiver).setColor(newColor);
        } catch (Exception e) {
            try {
                ((GSpace) ref.receiver).setColor(newColor);
            } catch (Exception e2) {
                System.out.println("can't change color");
            }
        }
        return ref;
    }

}