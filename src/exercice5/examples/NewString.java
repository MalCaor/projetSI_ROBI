package exercice5.examples;

import stree.parser.SNode;
import graphicLayer.*;

public class NewString implements Command{

    public NewString(){}

    public Reference run(Reference ref, SNode methode){
        ref.receiver = new GString(methode.get(2).contents());
        ref.addCommand("translate", new Translate());
        ref.addCommand("setDim", new SetDim());
        ref.addCommand("setColor", new SetColor());
        Environment.addReference(methode.get(methode.size()-1).contents(),ref);
        return ref;
    }
}
