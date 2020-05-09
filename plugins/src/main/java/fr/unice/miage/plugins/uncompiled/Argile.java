package fr.unice.miage.plugins.uncompiled;

import fr.unice.miage.common.plugins.PlugInBackground;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class Argile implements PlugInBackground {

    public Background createBackground(){
        BackgroundImage img = new BackgroundImage(new Image(Argile.class.getClassLoader().getResourceAsStream("/backgrounds/background_argile.png")), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT );
        return new Background(img);
    }
}