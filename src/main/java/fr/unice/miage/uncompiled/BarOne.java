package fr.unice.miage.uncompiled;

import fr.unice.miage.plugins.PlugInGUI1;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class BarOne implements PlugInGUI1 {

    public VBox createBar(){
        VBox barVBox = new VBox();
        Button playBtn = new Button();
        Button stopBtn = new Button();
        barVBox.getChildren().addAll(playBtn, stopBtn);
        return barVBox;
    }
}
