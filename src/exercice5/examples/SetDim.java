package exercice5.examples;

import java.awt.Dimension;

import graphicLayer.*;
import stree.parser.SNode;

//change les dimension d'un GSpace ou d'un GBounded
public class SetDim implements Command{
    public SetDim(){}

    public Reference run(Reference ref, SNode methode){
        try {
            //on tente de caster la reference en GSpace
            ((GSpace) ref.receiver).changeWindowSize(new Dimension(Integer.parseInt(methode.get(2).contents()), Integer.parseInt(methode.get(3).contents())));
        } catch (Exception e) {
            //Sinon on le cast en GBounded
            ((GBounded) ref.receiver).setHeight(Integer.parseInt(methode.get(2).contents()));
            ((GBounded) ref.receiver).setWidth(Integer.parseInt(methode.get(3).contents()));
        }

        return ref;
    }
}
