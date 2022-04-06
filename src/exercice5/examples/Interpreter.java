package exercice5.examples;

import stree.parser.SNode;

public class Interpreter {

    public void compute(Environment environment, SNode next) {
        
        int size = next.size();
        Reference r = null;
        String[] args = next.get(0).contents().split("\\.");

        
        if(size > 3 && next.get(3).hasChildren()) {
            // recursif
            // s'il y a une sous commande (ex: (GRect new) dans (space add robi (GRect new))) on l'isole et on l'execute en premier.
            // on effectue cette verification a chaque fois

            SNode sousCommande = next.get(3); //on recupere la sous Commande
            sousCommande.addChild(next.get(2)); //on ajoute en dernier paramètre le nom de l'objet
            compute(environment, sousCommande); // on execute la sous commande
            
            if(args.length > 1) {
                //On verifie si les objets appartiennent bien aux autres objets
                //ex: space.robi.im -> on verifie que space a bien un robi, et que robi a bien un im
                for(int i=0;i<args.length-1;i++) {
                    r = environment.getReferenceByName(args[i]).getChild(args[i+1]);
                }
            }else{
                r = environment.getReferenceByName(next.get(0).contents());
            }

            if(r==null){
                System.out.println("Erreur Interpreter : Reference " + next.get(0).contents() + " n'existe pas");
            }else{
                r.run(next);
            }
            
        }else{
            // une seul instruction

            if(args.length > 1) {
                //On verifie si les objets appartiennent bien aux autres objets
                //ex: space.robi.im -> on verifie que space a bien un robi, et que robi a bien un im
                for(int i=0;i<args.length-1;i++) {
                    r = environment.getReferenceByName(args[i]).getChild(args[i+1]);
                }
            }else{
                r = environment.getReferenceByName(next.get(0).contents());
            }

            if(r==null){
                System.out.println("Erreur Interpreter : Reference " + next.get(0).contents() + " n'existe pas");
            }else{
                r.run(next);
            }
        }

        

         
    }

}
