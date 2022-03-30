package exercice4;

import java.util.HashMap;

public class Environment {

    public HashMap<String, Reference> listRef;

    public void addReference(String string, Reference spaceRef) {
        listRef.put(string, spaceRef);
    }

    public Reference getReferenceByName(String receiverName) {
        return listRef.get(receiverName);
    }

}
