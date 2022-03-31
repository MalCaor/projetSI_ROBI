package exercice4;

import stree.parser.SNode;

public class Interpreter {

    public void compute(Environment environment, SNode next) {
        System.out.println(next.size());
        int i=0;
        while(next.hasChildren()) {
            if(next.get(i).hasChildren()) {
                /*int j=0;
                while(next.get(i).hasChildren()) {
                    System.out.println(next.get(i).get(j).contents());
                    j++;
                }*/
                compute(environment,next.get(i));
            }else{
                System.out.println(next.get(i).contents());
            }
            i++;
        }
        //environment.getReferenceByName(next.get(0).contents()).run(next);;
    }

}
