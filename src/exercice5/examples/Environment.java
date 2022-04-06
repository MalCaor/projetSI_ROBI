package exercice5.examples;

import java.util.HashMap;

public class Environment {

    static public HashMap<String, Reference> listRef;

    // constructor
    public Environment(){
        listRef = new HashMap<String, Reference>();
    }

    //ajout d'une reference a l'environnement
    static public void addReference(String string, Reference spaceRef) {
        listRef.put(string, spaceRef);
    }

    //retourne une reference via son nom
    static public Reference getReferenceByName(String receiverName) {
        return listRef.get(receiverName);
    }

}
