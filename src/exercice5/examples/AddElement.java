package projetsi.exercice5.examples;

import projetsi.graphicLayer.GElement;
import projetsi.graphicLayer.*;
import projetsi.stree.parser.SNode;

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


                    try {
                        ((GSpace) ref.receiver).addElement(g);
                        System.out.println("ajout du fils "+methode.get(2).contents()+" a "+methode.get(0).contents());
                        ref.addChild(methode.get(2).contents(), Environment.getReferenceByName(methode.get(2).contents()));
                        
                    } catch (Exception e) {
                        //TODO: handle exception
                        ((GBounded) ref.receiver).addElement(g);
                        System.out.println("ajout du fils "+methode.get(2).contents()+" a "+methode.get(0).contents());
                        ref.addChild(methode.get(2).contents(), Environment.getReferenceByName(methode.get(2).contents()));
                    }
                }
            }
        }
        return ref;
    }

}
