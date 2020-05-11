package fr.unice.miage.plugins.uncompiled.graphic_plugins;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.game_objects.Player;
import fr.unice.miage.common.plugins.PlugInGraphic;
import fr.unice.miage.common.sprite.RectangleSprite;
import fr.unice.miage.common.utils.Timer;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class MediumTank implements PlugInGraphic {

    public void init(Player player){
        player.setSprite(new RectangleSprite(player, 30, 30, Color.RED, true));
        player.setHealthBar(30, 5, Color.GREEN);
    }

    public void draw(Player player, CanvasGUI canvas) {
        player.getHealthBar().draw(player, canvas);
        player.getSprite().draw(canvas);
        Image img = getSprite(Timer.getChrono());
        double rot = -player.getRotation();

        double x = player.getX();
        double y = player.getY();
        double w = 40;
        double h = 30;
        ImageView iv = new ImageView(img);
        iv.setFitWidth(40);
        iv.setFitHeight(28);
        iv.setRotate(rot);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        Image rotatedImage = iv.snapshot(params, null);
        GraphicsContext c= canvas.getGraphicsContext();
        c.save();
        c.drawImage(rotatedImage, x - 10, y - 10);
        c.restore();

    }

    public void setToDead(Player player){
        player.getSprite().setColor(Color.GRAY);
    }

    private Image getSprite(double chrono){
        double currentTime = chrono;
        return new Image(MediumTank.class.getClassLoader().getResourceAsStream("/tanks/red_medium_tank.png"));
    }
}

