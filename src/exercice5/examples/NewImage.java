package projetsi.exercice5.examples;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import projetsi.graphicLayer.*;
import projetsi.stree.parser.SNode;

public class NewImage implements Command{
    public NewImage(){}

    public Reference run(Reference ref, SNode methode){
        BufferedImage image;
        try {
            image = ImageIO.read(new File(methode.get(2).contents()));
            ref.receiver = new GImage(image);
            ref.addCommand("translate", new Translate());
			ref.addCommand("setDim", new SetDim());
            Environment.addReference(methode.get(methode.size()-1).contents(),ref);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ref;
    }
}
