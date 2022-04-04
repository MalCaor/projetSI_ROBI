package exercice5.examples;

import stree.parser.SNode;

public class AddChild implements Command {
    public AddChild(){}

    @Override
    public Reference run(Reference ref, SNode methode) {
        if(methode.get(2) == null || methode.get(2).contents() == null){
            System.out.println("Erreur AddElement : pas de paramètre");
        } else { 
            if (Environment.getReferenceByName(methode.get(2).contents()) == null){
                System.out.println("Erreur AddElement : Reference "+ methode.get(2).contents() +" non trouvé");
            } else {
                Environment.getReferenceByName(methode.get(2).contents()).addChild(methode.get(2).contents(), Environment.getReferenceByName(methode.get(2).contents()));
            }
        }
        return ref;
    }
}
