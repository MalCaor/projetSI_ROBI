package exercice4;

import java.util.HashMap;

public class Environment {

    static public HashMap<String, Reference> listRef;

    // constructor
    public Environment(){
        listRef = new HashMap<String, Reference>();
    }

    //ajout d'une reference a la liste des reference de l'environment
    static public void addReference(String string, Reference spaceRef) {
        listRef.put(string, spaceRef);
    }

    //retourne la reference via son nom
    static public Reference getReferenceByName(String receiverName) {
        return listRef.get(receiverName);
    }

}
