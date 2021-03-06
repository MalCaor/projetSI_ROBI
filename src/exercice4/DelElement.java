package exercice4;

import graphicLayer.GElement;
import graphicLayer.GSpace;
import stree.parser.SNode;

//suppression d'un element d'un GSpace
public class DelElement implements Command{
    public DelElement() {
    }

    @Override
    public Reference run(Reference ref, SNode methode) {
        ((GSpace) ref.receiver).removeElement((GElement) (Environment.getReferenceByName(methode.get(2).contents())).receiver);
        return ref;
    }
}
