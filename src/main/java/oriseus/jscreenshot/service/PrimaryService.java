package oriseus.jscreenshot.service;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.WritableImage;
import javafx.scene.robot.Robot;
import javafx.stage.Screen;

public class PrimaryService {

	public WritableImage getScreenImage() {
				
		Robot robot = new Robot();
		Rectangle2D rectangle2d = Screen.getPrimary().getBounds();
		
		WritableImage writableImage = robot.getScreenCapture(null, rectangle2d);
		
		File directory = new File(PropertiesService.getProperties("TempFolder"));		
		if (!directory.exists()) {
			directory.mkdir();
		}

		File image = new File(directory + File.separator + "tempScreenShot.png");
		
		try {
		    ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "PNG", image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return writableImage;
	}
	
	public void getCutImage(File directory, int x, int y, int w, int h) {
		
		Robot robot = new Robot();
		Rectangle2D rectangle2d = new Rectangle2D(x, y, w, h);

		WritableImage writableImage = robot.getScreenCapture(null, rectangle2d);
    
		File image = new File(directory + "/newImage.png");
		
		try {
		    ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "PNG", image);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteTempImage() {
		File directory = new File(".." + File.separator + "jscreenshot" + File.separator + "src" + File.separator + "tempFolder");		
		if (!directory.exists()) {
			directory.mkdir();
		}
		
		File image = new File(directory + File.separator + "tempScreenShot.png");
		
		image.delete();
	}
}
