package exercice4;

import graphicLayer.GElement;
import graphicLayer.GSpace;
import stree.parser.SNode;

public class SetDim implements Command{
    public SetDim(){}

    public Reference run(Reference ref, SNode methode){
        return ref;
    }
}
