package exercice4;

import java.util.HashMap;
import java.util.Map;
import stree.parser.SNode;

public class Reference {

    Object receiver;
    Map<String, Command> primitives;
    
    public Reference(Object receiver) {
        this.receiver = receiver;
        primitives = new HashMap<String, Command>();
    }

    Command getCommandByName(String selector){
        return primitives.get(selector);
    }

    void addCommand(String selector, Command primitive){
        primitives.put(selector, primitive);
    }

    Reference run(SNode method){
        return null;
    }
}
