package oriseus.jscreenshot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oriseus.jscreenshot.service.PrimaryService;

import java.io.IOException;

public class App extends Application {
	
    @Override
    public void start(Stage stage) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml")); 
    	Scene scene = new Scene(root);
    	
    	stage.setScene(scene);
           
    	stage.setTitle("JScreenShot");

    	stage.show();
    }
    
    public static void main(String[] args) {
    	launch();  	
    }
}