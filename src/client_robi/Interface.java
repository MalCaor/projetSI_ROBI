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
        	s=contro.getTextFieldVal();//On met dans s la valeur du textArea1 
        });
        
        
 
    }
    
    public void setVal(String strEntree) {//cette méthode permet de mettre dans le second textArea du modèle la valeur de strEntree
    	contro.setTAval(strEntree);
    }
    public void finishInter() {//cette methode ferme l'interface graphique
    	Platform.exit();
    }
    
    
	public void main(String[] args) throws InterruptedException {
		launch(args);
	}


}
