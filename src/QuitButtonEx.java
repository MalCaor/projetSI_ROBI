package projetsi;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * ZetCode JavaFX tutorial
 *
 * This program has a Quit button. Clicking
 * on the button terminates the application.
 *
 * Author: Jan Bodnar
 * Website: zetcode.com
 * Last modified: June 2015
 * 
 * http://zetcode.com/gui/javafx
 */
public class QuitButtonEx extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        initUI(stage);
    }
   
    private void initUI(Stage stage) throws IOException {
        Button btn = new Button();
        btn.setText("Quit");
        btn.setOnAction((ActionEvent event) -> {
            Platform.exit();
        });

        TextField ip=new TextField();
        Button valIp = new Button();
        valIp.setText("Entrer");
        
        valIp.setOnAction((ActionEvent event) -> {
        	String ipPourSock=ip.getText();
        	System.out.println(ipPourSock);
        }); 
        HBox root = new HBox();
        root.setPadding(new Insets(25));
        root.getChildren().add(ip);
        root.getChildren().add(valIp);
        root.getChildren().add(btn);
        Scene scene = new Scene(root, 280, 200);

        stage.setTitle("Client ROBI");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}