package exercice5.examples;

import graphicLayer.GElement;
import graphicLayer.GSpace;
import stree.parser.SNode;

public class AddElement implements Command{
    public AddElement(){}

    @Override
    public Reference run(Reference ref, SNode methode) {
        if(methode.get(2) == null || methode.get(2).contents() == null){
            System.out.println("Erreur AddElement : pas de paramètre");
        } else { 
            if (Environment.getReferenceByName(methode.get(2).contents()) == null){
                System.out.println("Erreur AddElement : Reference "+ methode.get(2).contents() +" non trouvé");
            } else {
                GElement g = ((GElement) (Environment.getReferenceByName(methode.get(2).contents())).receiver);
                if(g == null){
                    System.out.println("Erreur AddElement : element is null");
                } else{
                    ((GSpace) ref.receiver).addElement(g);
                }
            }
        }
        return ref;
    }

}
