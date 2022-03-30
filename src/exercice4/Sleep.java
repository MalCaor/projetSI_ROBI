package exercice4;

import stree.parser.SNode;

public class Sleep implements Command{

    public Sleep() {
    }

    @Override
    public void run(Reference ref, SNode method) {
        try {
            Thread.sleep(Integer.parseInt(method.get(2).contents()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
