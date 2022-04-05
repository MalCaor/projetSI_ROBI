package exercice5.examples;

import java.awt.Dimension;

import graphicLayer.*;
import stree.parser.SNode;

public class SetDim implements Command{
    public SetDim(){}

    public Reference run(Reference ref, SNode methode){
        try {
            ((GSpace) ref.receiver).changeWindowSize(new Dimension(Integer.parseInt(methode.get(2).contents()), Integer.parseInt(methode.get(3).contents())));
        } catch (Exception e) {
            //TODO: handle exception
            ((GBounded) ref.receiver).setHeight(Integer.parseInt(methode.get(2).contents()));
            ((GBounded) ref.receiver).setWidth(Integer.parseInt(methode.get(3).contents()));
        }

        return ref;
    }
}
