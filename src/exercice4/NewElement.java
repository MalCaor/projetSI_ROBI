package exercice4;

import stree.parser.SNode;
import graphicLayer.*;

public class NewElement implements Command{
    public NewElement(){}

    public void run(Reference ref, SNode methode){
        String objet = methode.get(3).contents();
        
        switch(objet){
            case "Rect":
            
                break;
            case "Oval":
            
                break;
            case "Image":
                
                break;
            case "Label":
                break;
        }
    }
}
