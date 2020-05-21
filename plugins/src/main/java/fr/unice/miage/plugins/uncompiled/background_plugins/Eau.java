package fr.unice.miage.plugins.uncompiled.background_plugins;

import fr.unice.miage.common.plugins.PlugInBackground;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class Eau implements PlugInBackground {

    public Background createBackground(){
        BackgroundImage img;
        try {
            img = new BackgroundImage(new Image(Eau.class.getClassLoader().getResourceAsStream("/backgrounds/background_eau.png")), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
        } catch (Exception e){
            img = new BackgroundImage(new Image("/backgrounds/background_eau.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
        }
        return new Background(img);
    }
}
