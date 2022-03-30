package exercice4;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import graphicLayer.*;
import stree.parser.SNode;

public class NewImage implements Command{
    public NewImage(){}

    public void run(Reference ref, SNode methode){
        BufferedImage image;
        try {
            image = ImageIO.read(new File(methode.get(2).contents()));
            ((GSpace)ref.receiver).addElement(new GImage(image));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
