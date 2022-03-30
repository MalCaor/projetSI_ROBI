package exercice4;

import graphicLayer.GElement;
import graphicLayer.GSpace;
import stree.parser.SNode;

public class AddElement implements Command{
    public AddElement() {
    }

    @Override
    public void run(Reference ref, SNode methode) {
        ((GSpace) ref.receiver).addElement((GElement) (Environment.getReferenceByName(methode.get(2).contents())).receiver);
    }

}
