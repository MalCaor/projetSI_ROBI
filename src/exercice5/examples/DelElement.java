package exercice5.examples;

import graphicLayer.GElement;
import graphicLayer.*;
import stree.parser.SNode;

//suppression d'un element d'une reference
public class DelElement implements Command{
    public DelElement() {
    }

    @Override
    public Reference run(Reference ref, SNode methode) {
        try {
            // on tente de caster la reference en GSpace
            ((GSpace) ref.receiver).removeElement((GElement) (Environment.getReferenceByName(methode.get(2).contents())).receiver);
        } catch (Exception e) {
            //Sinon on le cast en GBounded
            ((GBounded) ref.receiver).removeElement((GElement) (Environment.getReferenceByName(methode.get(2).contents())).receiver);
        }
        return ref;
    }
}
