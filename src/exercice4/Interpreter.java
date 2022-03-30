package exercice4;

import stree.parser.SNode;

public class Interpreter {

    public void compute(Environment environment, SNode next) {
        environment.getReferenceByName(next.get(0).contents()).run(next);;
    }

}
