package client_robi;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Interface extends Application{
	public static String s;
	public static Control contro;
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../modele.fxml"));
        Parent root = loader.load();
        //primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("img/EdenCodingIcon.png")));
        primaryStage.setTitle("Projet L3");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        contro=loader.getController();
        contro.b1.setOnAction((ActionEvent event) -> {
        	s=contro.getTextFieldVal();
            
            //System.out.println(">"+s+"<");
        });
        
        
 
    }
    
    public void setVal(String s) {
    	contro.setTAval(s);
    }
    
	public static void main(String[] args) throws InterruptedException {
		launch(args);
		//System.out.println(">"+s+"<");
		//Thread.sleep(10000);
	}


}
