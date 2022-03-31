package exercice4;

import stree.parser.SNode;

public class Interpreter {

    public void compute(Environment environment, SNode next) {
        int size = next.size();
        if(size <= 3){
            // une seul instruction
            environment.getReferenceByName(next.get(0).contents()).run(next);
        } else {
            // itération
            SNode sousCommande = next.get(3);
            sousCommande.addChild(next.get(2));
            compute(environment, sousCommande);
            
            environment.getReferenceByName(next.get(0).contents()).run(next);
        }
    }

}
