package exercice4;

import stree.parser.SNode;

// fait attendre le Thread
public class Sleep implements Command{

    public Sleep() {
    }

    @Override
    public Reference run(Reference ref, SNode method) {
        try {
            Thread.sleep(Integer.parseInt(method.get(2).contents()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ref;
    }
}
