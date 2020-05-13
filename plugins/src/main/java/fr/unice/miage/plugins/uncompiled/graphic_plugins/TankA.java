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
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class TankA implements PlugInGraphic {

    public void init(Player player){
        player.setSprite(new RectangleSprite(player, 30, 30, Color.RED, true));
        player.setHealthBar(30, 5, Color.GREEN);
    }

    public void draw(Player player, CanvasGUI canvas) {
        player.getHealthBar().draw(player, canvas);
        player.getSprite().draw(canvas);

        double rot = player.getRotation();
        double wRot = player.getWeaponRotation();

        double x = player.getX();
        double y = player.getY();
        double w = 30;
        double h = 40;

        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        Image hull = new Image(TankA.class.getClassLoader().getResourceAsStream("/tanks2/colorA/hulls/Hull_01.png"));
        ImageView ivHull = new ImageView(hull);
        ivHull.setFitWidth(w);
        ivHull.setFitHeight(h);
        //Image rotatedHull = ivHull.snapshot(params, null);

        Image gun = new Image(TankA.class.getClassLoader().getResourceAsStream("/tanks2/colorA/guns/Gun_01.png"));
        ImageView ivGun = new ImageView(gun);
        ivGun.setFitWidth(h * 0.38);
        ivGun.setFitHeight(h * 0.85);
        ivGun.setX(x + w);
        ivGun.setY(y + h * 0.5);

        ivGun.setRotate(wRot - rot);


        Image rotatedGun = ivGun.snapshot(params, null);


        Image track;
        if(Timer.getChrono() % 2 == 0){
            track = new Image(TankA.class.getClassLoader().getResourceAsStream("/tanks2/colorA/tracks/Track_1_A.png"));
        } else track = new Image(TankA.class.getClassLoader().getResourceAsStream("/tanks2/colorA/tracks/Track_1_B.png"));

        ImageView ivTrackL = new ImageView(track);
        ivTrackL.setFitWidth(w);
        ivTrackL.setFitHeight(h);
        ImageView ivTrackR = new ImageView(track);
        ivTrackR.setFitWidth(w);
        ivTrackR.setFitHeight(h);
        ivTrackL.setX(x);
        ivTrackL.setY(y);
        ivTrackR.setX(x + w);
        ivTrackR.setY(y + h);

        GridPane gp = new GridPane();
        gp.getChildren().addAll(ivTrackL, ivTrackR, ivHull);
        gp.setRotate(rot);
        gp.getChildren().add(ivGun);
        Image rotated = gp.snapshot(params, null);

        GraphicsContext c= canvas.getGraphicsContext();

        c.save();
        c.drawImage(rotated, x, y);
        c.restore();

    }

    public void setToDead(Player player){
        player.getSprite().setColor(Color.GRAY);
    }

    private Image getSprite(double chrono){
        double currentTime = chrono;
        return new Image(TankA.class.getClassLoader().getResourceAsStream("/tanksA/red_medium_tank.png"));
    }
}

