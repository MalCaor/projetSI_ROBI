package projetsi.exercice4;

import java.awt.Color;

import projetsi.graphicLayer.GElement;
import projetsi.graphicLayer.GRect;
import projetsi.graphicLayer.GSpace;
import projetsi.stree.parser.SNode;
import projetsi.tools.Tools;

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
            System.out.println("can't change color");
        }
        return ref;
    }

}