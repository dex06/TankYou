package fr.unice.miage.plugins.uncompiled.graphic_plugins;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.game_objects.Player;
import fr.unice.miage.common.plugins.PlugInGraphic;
import fr.unice.miage.common.sprite.RectangleSprite;
import fr.unice.miage.common.utils.ImageLoader;
import fr.unice.miage.common.utils.Timer;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public class MediumTankB implements PlugInGraphic {

    public void init(Player player){
        player.setSprite(new RectangleSprite(player, 30, 40, Color.RED, true));
        player.setHealthBar(30, 5, Color.GREEN);
    }

    public void draw(Player player, CanvasGUI canvas) {

        double rot = player.getRotation();
        double wRot = player.getWeaponRotation();

        player.getHealthBar().draw(player, canvas);
        player.getSprite().setRotation(rot);
        //player.getSprite().draw(canvas);

        double x = player.getX();
        double y = player.getY();
        double w = 30;
        double h = 40;



        Image hull = ImageLoader.loadImage("/tanks2/colorB/hulls/Hull_01.png", MediumTankB.class);
        ImageView ivHull = new ImageView(hull);
        ivHull.setFitWidth(w);
        ivHull.setFitHeight(h);


        Image gun = ImageLoader.loadImage("/tanks2/colorB/guns/Gun_01.png", MediumTankB.class);

        ImageView ivGun = new ImageView(gun);
        ivGun.setFitWidth(w);
        ivGun.setFitHeight(h);


        Image track;
        if((int) Timer.getChrono() % 2 == 0){
            track = ImageLoader.loadImage("/tanks2/colorB/tracks/Track_1_A.png", MediumTankB.class);
        } else {
            track = ImageLoader.loadImage("/tanks2/colorB/tracks/Track_1_B.png", MediumTankB.class);
        }

        ImageView ivTrackL = new ImageView(track);
        ivTrackL.setFitWidth(w);
        ivTrackL.setFitHeight(h);
        ImageView ivTrackR = new ImageView(track);
        ivTrackR.setFitWidth(w);
        ivTrackR.setFitHeight(h);
        ivTrackR.setX(w);
        ivTrackR.setY(h);

        GraphicsContext c = canvas.getGraphicsContext();
        c.save();
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        GridPane gp = new GridPane();
        gp.getChildren().addAll(ivTrackL, ivTrackR, ivHull);
        Image nonRotated = gp.snapshot(params, null);
        Image gunImage = ivGun.snapshot(params, null);

        c.translate(x, y);
        c.transform(new Affine(new Rotate(rot, w/2, h/2)));
        c.drawImage(nonRotated, 0, 0);
        c.transform(new Affine(new Rotate(wRot-rot, w/2, 0.67*h)));
        c.drawImage(gunImage, 0, 0);
        c.restore();
    }

    public void setToDead(Player player){
        player.getSprite().setColor(Color.GRAY);
    }
}

