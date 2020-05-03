package fr.unice.miage.common.sprite;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.game_objects.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;

public class ObstacleSprite extends Sprite {
    public ObstacleSprite(double width, double height, Paint color) {
        super(width, height, color);
    }

    @Override
    public Shape getBoundingShape() {
        return null;
    }
}
