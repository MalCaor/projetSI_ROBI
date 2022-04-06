package projetsi.exercice5.examples;

import java.io.IOException;
import java.util.List;
import java.util.Iterator;
import projetsi.stree.parser.*;


public class Exercice5 {

    public void oneShot(String script){
        System.out.println("execution de one Shot");

        //--------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------
        Environment environment = new Environment();

        System.out.println("creation env");

        new Exercice4_2_0();
        //--------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------
        System.out.println("Exercice4_2_0 crée");

        // creation du parser
        SParser<SNode> parser = new SParser<>();
        // compilation
        List<SNode> compiled = null;
        try {
            System.out.println("execution script");
            compiled = parser.parse(script);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // execution des s-expressions compilees
        System.out.println("execution des s-expressions compilees");
        Iterator<SNode> itor = compiled.iterator();
        while (itor.hasNext()) {
            new Interpreter().compute(environment, itor.next());
        }
    }
}
