package exercice5.examples;

import java.util.HashMap;
import java.util.Map;
import stree.parser.SNode;

public class Reference {

    Object receiver;
    Map<String, Command> primitives;

    // list children
    Map<String, Reference> listChildren;
    
    public Reference(Object receiver) {
        this.receiver = receiver;
        primitives = new HashMap<String, Command>();
        listChildren = new HashMap<String, Reference>();
    }

    Command getCommandByName(String selector){
        return primitives.get(selector);
    }

    void addCommand(String selector, Command primitive){
        primitives.put(selector, primitive);
    }

    // children

    void addChild(String childName, Reference ref){
        listChildren.put(childName, ref);
    }

    Reference getChild(String name){
        return listChildren.get(name);
    }

    void run(SNode method){
        if(method.get(1).contents() != null || method.get(1).contents() != ""){
            if(this.primitives.get(method.get(1).contents()) != null){
                this.primitives.get(method.get(1).contents()).run(this, method);
            } else {
                System.out.println("Erreur Reference : Methode " + method.get(1).contents() + " est pas dans la Ref " + method.get(0).contents());
            }
            
        } else {
            System.out.println("Erreur Reference : Methode " + method.get(1).contents() + " vide");
        }
    }

    public Object getReceiver() {
        return receiver;
    }
}
