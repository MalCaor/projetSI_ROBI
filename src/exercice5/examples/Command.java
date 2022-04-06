package projetsi.exercice5.examples;

import projetsi.stree.parser.SNode;

public interface Command {
    abstract public Reference run(Reference ref, SNode methode);
}
