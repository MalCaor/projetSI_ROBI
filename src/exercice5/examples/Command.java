package exercice5.examples;

import stree.parser.SNode;

public interface Command {
    abstract public Reference run(Reference ref, SNode methode);
}
