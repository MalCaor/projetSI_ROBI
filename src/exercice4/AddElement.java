package exercice4;

import graphicLayer.GElement;
import graphicLayer.GSpace;
import stree.parser.SNode;

//Ajout d'un element a un GSpace
public class AddElement implements Command{
    public AddElement(){}

    @Override
    public Reference run(Reference ref, SNode methode) {
        if(methode.get(2) == null || methode.get(2).contents() == null){// verification du paramètre
            System.out.println("Erreur AddElement : pas de paramètre");
        } else { 
            if (Environment.getReferenceByName(methode.get(2).contents()) == null){ // on verifie que le paramètre existe bien dans environnement
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
