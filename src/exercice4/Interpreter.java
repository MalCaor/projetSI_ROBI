package exercice4;

import stree.parser.SNode;

public class Interpreter {

    public void compute(Environment environment, SNode next) {
        int size = next.size();
        if(size > 3 && next.get(3).hasChildren()) {
            // itération 
            // s'il y a une sous commande (ex: (GRect new) dans (space add robi (GRect new))) on l'isole et on l'execute en premier.
            // on effectue cette verification a chaque fois
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
