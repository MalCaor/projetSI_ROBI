package exercice4;

import stree.parser.SNode;
import graphicLayer.*;

public class NewString {

    public NewString(){}

    public void run(Reference ref, SNode methode){
        ((GSpace)ref.receiver).addElement(new GString(methode.get(2).contents()));
    }
}
