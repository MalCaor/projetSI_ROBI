package client_robi;



import static java.util.logging.Level.SEVERE;

import java.io.File;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

public class Control {

	private File fic;

    public Button b1;
    public TextArea textArea1;
    public TextArea textArea2;
    
    public void initialize() {
    }

    public void b1_exec(ActionEvent event) {
    	
    	System.out.println("textArea1 = "+textArea1.getText());
    }
    public String getTextFieldVal() {
    	return textArea1.getText();
    }
    
    public void setTAval(String s) {
    	textArea2.setText(s);
    }
    
    
    public void openFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        //only allow text files to be selected using chooser
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt")
        );

        //set initial directory somewhere user will recognise
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        
        //let user select file
        File fileToLoad = fileChooser.showOpenDialog(null);
        
        if (fileToLoad != null) {
        	System.out.println("file = "+fileToLoad.getPath());
        }
        else {
        	System.out.println("file = null");
        }

     }


    public void saveFile(ActionEvent event) {
    }
}