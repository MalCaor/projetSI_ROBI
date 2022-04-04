package exercice5.examples;

import stree.parser.SNode;

public class Interpreter {

    public void compute(Environment environment, SNode next) {
        int size = next.size();

        Reference r = null;

        //System.out.println(next.get(0).contents());
        String[] args = next.get(0).contents().split("\\.");
        /*for(String a : args){
            System.out.println(a);
        }
        System.out.println("oui");*/

        if(args.length > 1) {

            for(int i=0;i<args.length-1;i++) {
                r = environment.getReferenceByName(args[i]).getChild(args[i+1]);
            }
            
        }else{
            r = environment.getReferenceByName(next.get(0).contents());
        }
        
        if(r==null){
            System.out.println("Erreur Interpreter : Reference " + next.get(0).contents() + " n'existe pas");
        }else{

            if(size > 3 && next.get(3).hasChildren()) {
                // recursif
                SNode sousCommande = next.get(3);
                sousCommande.addChild(next.get(2));
                compute(environment, sousCommande);
                
                r.run(next);
                //System.out.println("Reference : " + next.get(0).contents());
                //environment.getReferenceByName(next.get(0).contents()).run(next);
            }else{
                // une seul instruction
                r.run(next);
            }

        }

         
    }

}
