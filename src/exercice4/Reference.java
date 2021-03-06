package exercice4;

import java.util.HashMap;
import java.util.Map;
import stree.parser.SNode;

public class Reference {

    Object receiver;
    Map<String, Command> primitives; // liste des commandes auquel l'objet receiver a acces
    
    public Reference(Object receiver) {
        this.receiver = receiver;
        primitives = new HashMap<String, Command>();
    }

    //retourne la command via son nom
    Command getCommandByName(String selector){
        return primitives.get(selector);
    }

    //ajout d'une commande a la liste de commande du receiver
    void addCommand(String selector, Command primitive){
        primitives.put(selector, primitive);
    }

    //execute la commande voulu sur le receiver
    void run(SNode method){
        if(method.get(1).contents() != null || method.get(1).contents() != ""){
            if(this.primitives.get(method.get(1).contents()) != null){
                this.primitives.get(method.get(1).contents()).run(this, method);
            } else {
                System.out.println("Erreur Reference : Methode " + method.get(1).contents() + " est pas dans la Ref");
            }
            
        } else {
            System.out.println("Erreur Reference : Methode " + method.get(1).contents() + " vide");
        }
    }

    public Object getReceiver() {
        return receiver;
    }
}
