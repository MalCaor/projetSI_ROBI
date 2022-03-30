package exercice4;

import stree.parser.SNode;

public interface Command {
    abstract public Reference run(Reference ref, SNode methode);
}
