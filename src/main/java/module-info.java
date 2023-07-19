module oriseus.jscreenshot {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.desktop;
    requires javafx.graphics;
    requires javafx.swing;

    opens oriseus.jscreenshot to javafx.fxml;
    opens oriseus.jscreenshot.controller to javafx.fxml;
    
    exports oriseus.jscreenshot;
    exports oriseus.jscreenshot.controller;
}
