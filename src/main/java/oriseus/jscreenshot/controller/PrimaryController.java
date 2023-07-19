package oriseus.jscreenshot.controller;

import java.io.File;
import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import oriseus.jscreenshot.service.PrimaryService;
import oriseus.jscreenshot.service.PropertiesService;

public class PrimaryController {

	@FXML
	private ImageView imageView;
	
	@FXML
	private BorderPane borderPane;
	
	@FXML
	Rectangle dragBox;
	
	@FXML
	private Button addImageButton;
	@FXML
	private Button getDirectoryButton;
	@FXML
	private Button exitButton;
	
	@FXML
	private Text directoryText;
	
	private PrimaryService primaryService;
	private Image image;
	private File directory;
	private int x,y,w,h = 1;
	private int counter = 0;
	
	@FXML
    private void initialize() {
		primaryService = new PrimaryService();

		primaryService.getScreenImage();
		
		File directory = new File(PropertiesService.getProperties("TempFolder"));	
		if (!directory.exists()) {
			directory.mkdir();
		}
		
		File file = new File(directory + File.separator + "tempScreenShot.png");
		
        image = new Image(file.toURI().toString());
        imageView.setImage(image);
	    
	    imageView.fitWidthProperty().bind(borderPane.widthProperty());
	    imageView.fitHeightProperty().bind(borderPane.heightProperty());
	    
	    imageView.setImage(image);
	}
	
	
    @FXML
    private void getCutImage() throws IOException {
    	
    	if (directory == null) return;
    	
    	Scene scene = exitButton.getScene();
    	dragBox.setVisible(false);
        scene.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {
            
        	@Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED){
                    dragBox.setVisible(true);
                    dragBox.setTranslateX(mouseEvent.getX());
                    dragBox.setTranslateY(mouseEvent.getY());                   
                    
                    if (counter < 1) {
						x = (int) mouseEvent.getScreenX();
						y = (int) mouseEvent.getScreenY();
						++counter;
					}
                }
                
                if (mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED && dragBox.isVisible()){
                    dragBox.setWidth(mouseEvent.getX() - dragBox.getTranslateX());
                    dragBox.setHeight(mouseEvent.getY() - dragBox.getTranslateY());    
                    
                    w = (int) (mouseEvent.getScreenX() - x);
                    h = (int) (mouseEvent.getScreenY() - y);                    
                }
                
                if(mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED) {
                	dragBox.setVisible(false);                	
                	primaryService.getCutImage(directory, x, y, w, h);
                }
            }
        });       
    }

    @FXML
    public void getDirectory() {
    	DirectoryChooser directoryChooser = new DirectoryChooser();  	
    	directory = directoryChooser.showDialog(getDirectoryButton.getScene().getWindow());
    	directoryText.setText(directory.getAbsolutePath());
    }
    
    @FXML
    public void exit() throws IOException {
    	primaryService.deleteTempImage();
        System.exit(0);
    }
}
