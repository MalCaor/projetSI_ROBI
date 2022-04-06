package projetsi.exercice4;

import projetsi.graphicLayer.GElement;
import projetsi.graphicLayer.GSpace;
import projetsi.stree.parser.SNode;

public class DelElement implements Command{
    public DelElement() {
    }

    @Override
    public Reference run(Reference ref, SNode methode) {
        ((GSpace) ref.receiver).removeElement((GElement) (Environment.getReferenceByName(methode.get(2).contents())).receiver);
        return ref;
    }
}
