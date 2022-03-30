package exercice4;
import java.awt.Color;
import graphicLayer.GRect;
import graphicLayer.GSpace;
import stree.parser.SNode;

public class SetColor implements Command {
    Color newColor;

    public SetColor(Color newColor) {
        this.newColor = newColor;
    }

    @Override
    public void run(Reference ref, SNode methode) {
        try {
            ((GSpace) ref.receiver).setColor(newColor);
        } catch (Exception e) {
            ((GRect) ref.receiver).setColor(newColor);
        }
    }

}