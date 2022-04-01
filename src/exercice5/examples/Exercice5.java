package exercice5.examples;

import java.io.IOException;
import java.util.List;
import java.util.Iterator;
import exercice4.*;
import stree.parser.*;
import graphicLayer.*;


public class Exercice5 {

    public void oneShot(String script){

        //--------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------
        Environment environment = new Environment();
        new Exercice4_2_0();
        //--------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------


        // creation du parser
        SParser<SNode> parser = new SParser<>();
        // compilation
        List<SNode> compiled = null;
        try {
            compiled = parser.parse(script);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // execution des s-expressions compilees
        Iterator<SNode> itor = compiled.iterator();
        while (itor.hasNext()) {
            new Interpreter().compute(environment, itor.next());
        }
    }
}
