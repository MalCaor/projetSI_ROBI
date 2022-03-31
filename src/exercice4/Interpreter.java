package exercice4;

import stree.parser.SNode;

public class Interpreter {

    public void compute(Environment environment, SNode next) {
        int size = next.size();
        if(size <= 3){
            // une seul instruction
<<<<<<< HEAD
            Environment.getReferenceByName(next.get(0).contents()).run(next);
=======
            if(environment.getReferenceByName(next.get(0).contents()) == null){
                System.out.println("Erreur Interpreter : Reference " + next.get(0).contents() + " n'existe pas");
            } else {
                environment.getReferenceByName(next.get(0).contents()).run(next);
            }
>>>>>>> f25b0565bfa03c059d13824ea2647304c16c91de
        } else {
            // itération
            SNode sousCommande = next.get(3);
            sousCommande.addChild(next.get(2));
            compute(environment, sousCommande);
            
<<<<<<< HEAD
            Environment.getReferenceByName(next.get(0).contents()).run(next);
=======
            if(environment.getReferenceByName(next.get(0).contents()) == null){
                System.out.println("Erreur Interpreter : Reference " + next.get(0).contents() + " n'existe pas");
            } else {
                environment.getReferenceByName(next.get(0).contents()).run(next);
            }
>>>>>>> f25b0565bfa03c059d13824ea2647304c16c91de
        }
    }

}
