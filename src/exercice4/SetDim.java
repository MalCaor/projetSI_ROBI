package exercice4;

import graphicLayer.GSpace;
import stree.parser.SNode;


//Change les dimensions d'un GSpace
public class SetDim implements Command{
    public SetDim(){}

    public Reference run(Reference ref, SNode methode){
        try {
            ((GSpace)ref.receiver).setSize(Integer.parseInt(methode.get(2).contents()), Integer.parseInt(methode.get(3).contents()));
        } catch (Exception e) {
            //TODO: handle exception
        }

        return ref;
    }
}
