package fr.unice.miage.common.utils;

import javafx.scene.image.Image;

public class ImageLoader {

    public static Image loadImage(String path, Class<?> cls){
        Image img;
        try {
            img = new Image(cls.getClassLoader().getResourceAsStream(path));
        } catch (Exception e) {
            img = new Image(path);
        }
        return img;
    }
}
