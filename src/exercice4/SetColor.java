package exercice4;

import java.awt.Color;
import graphicLayer.GRect;
import graphicLayer.GSpace;
import stree.parser.SNode;
import tools.Tools;

public class SetColor implements Command {
    Color newColor;

    public SetColor() {
    }

    @Override
    public void run(Reference ref, SNode methode) {
        newColor = Tools.getColorByName(methode.get(2).contents());
        try {
            ((GSpace) ref.receiver).setColor(newColor);
        } catch (Exception e) {
            ((GRect) ref.receiver).setColor(newColor);
        }
    }

}