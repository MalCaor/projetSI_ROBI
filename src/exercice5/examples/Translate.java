package exercice5.examples;

import java.awt.Point;

import graphicLayer.*;
import stree.parser.SNode;

public class Translate implements Command{
    public Translate(){}

    public Reference run(Reference ref, SNode methode){
        ((GElement)ref.receiver).translate(new Point(Integer.parseInt(methode.get(2).contents()), Integer.parseInt(methode.get(3).contents())));
        return ref;
    }
}
