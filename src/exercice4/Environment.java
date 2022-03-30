package exercice4;

import java.util.Dictionary;

public class Environment {

    public Dictionary<String, Reference> listRef;

    public void addReference(String string, Reference spaceRef) {
        listRef.put(string, spaceRef);
    }

    public Reference getReferenceByName(String receiverName) {
        return listRef.get(receiverName);
    }

}
