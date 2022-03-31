package exercice4;

import stree.parser.SNode;

public class Interpreter {

    public void compute(Environment environment, SNode next) {
        int size = next.size();
        if(size > 3 && next.get(3).hasChildren()) {
            // itération
            SNode sousCommande = next.get(3);
            sousCommande.addChild(next.get(2));
            compute(environment, sousCommande);
            
            if(environment.getReferenceByName(next.get(0).contents()) == null){
                System.out.println("Erreur Interpreter : Reference " + next.get(0).contents() + " n'existe pas");
            } else {
                environment.getReferenceByName(next.get(0).contents()).run(next);
            }
        }else{
            // une seul instruction
            if(environment.getReferenceByName(next.get(0).contents()) == null){
                System.out.println("Erreur Interpreter : Reference " + next.get(0).contents() + " n'existe pas");
            } else {
                environment.getReferenceByName(next.get(0).contents()).run(next);
            }
        } 
    }

}
