package fr.unice.miage.plugins.uncompiled.obstacle_plugins;

import fr.unice.miage.common.game_objects.Obstacle;
import fr.unice.miage.common.game_objects.Player;
import fr.unice.miage.common.game_objects.Projectile;
import fr.unice.miage.common.geom.Vector2;
import fr.unice.miage.common.plugins.PlugInObstacle;
import fr.unice.miage.common.sprite.ObstacleSprite;
import fr.unice.miage.common.utils.ImageLoader;
import fr.unice.miage.common.utils.Randomizer;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Multi implements PlugInObstacle {

    private Obstacle generateTree(){
        Image img;
        int rota = 1 + (int)(Math.random() * 360);
        int rdmObject = 1 + (int)(Math.random()*4);
        Obstacle obs = null;
        if(rdmObject == 1){
            int rdm = 1 + (int)(Math.random()*4);
            switch (rdm){
                case 0:
                    img = ImageLoader.loadImage("/arbre/arbre1.png", Multi.class);
                    break;
                case 1:
                    img = ImageLoader.loadImage("/arbre/arbre2.png", Multi.class);
                    break;
                case 3:
                    img = ImageLoader.loadImage("/arbre/arbre3.png", Multi.class);
                    break;
                case 4:
                    img = ImageLoader.loadImage("/arbre/arbre4.png", Multi.class);
                    break;
                default:
                    img = ImageLoader.loadImage("/arbre/arbre1.png", Multi.class);
                    break;
            }
            Vector2 vctr = new Vector2(Math.random()*600, Math.random()*600);
            ObstacleSprite sprite = new ObstacleSprite(vctr, 50, 50, Color.TRANSPARENT, img, "circle", rota);
            obs = new Obstacle(this, vctr, sprite);

        }
        else if(rdmObject == 2){
            img = ImageLoader.loadImage("/mur/mur1.png", Multi.class);
            Vector2 vctr = new Vector2(Math.random()*600, Math.random()*600);
            ObstacleSprite sprite = new ObstacleSprite(vctr, 150, 25, Color.TRANSPARENT, img, "rectangle", rota);
            obs = new Obstacle(this, vctr, sprite);
        }
        else if(rdmObject == 3){
            img = ImageLoader.loadImage("/mur/mur2.png", Multi.class);
            Vector2 vctr = new Vector2(Math.random()*600, Math.random()*600);
            ObstacleSprite sprite = new ObstacleSprite(vctr, 36, 150, Color.TRANSPARENT, img, "rectangle", rota);
            obs = new Obstacle(this, vctr, sprite);
        }
        else if(rdmObject == 4){
            int rdm = 1 + (int)(Math.random()*2);
            Vector2 vctr = null;
            ObstacleSprite sprite = null;
            switch (rdm){
                case 1:
                    img = ImageLoader.loadImage("/car/car1.png", Multi.class);
                    if(Randomizer.getRandomIntInRange(1,3) % 2 == 0){
                        vctr = new Vector2(Math.random()*600, Math.random()*600);
                        Vector2 newPos = new Vector2(vctr.getX()-20, vctr.getY()+20);
                        ImageView iv = new ImageView(img);
                        iv.setRotate(90);
                        SnapshotParameters params = new SnapshotParameters();
                        params.setFill(Color.TRANSPARENT);
                        Image rotatedImage = iv.snapshot(params, null);
                        sprite = new ObstacleSprite(newPos, 50, 30, Color.TRANSPARENT, rotatedImage, "rectangle", rota);
                        obs = new Obstacle(this, newPos, sprite);
                    } else {
                        vctr = new Vector2(Math.random() * 600, Math.random() * 600);
                        sprite = new ObstacleSprite(vctr, 30, 50, Color.TRANSPARENT, img, "rectangle");
                        obs = new Obstacle(this, vctr, sprite);
                    }
                    break;
                case 2:
                    img = ImageLoader.loadImage("/car/car2.png", Multi.class);
                    if(Randomizer.getRandomIntInRange(1,3) % 2 == 0){
                        vctr = new Vector2(Math.random()*600, Math.random()*600);
                        Vector2 newPos = new Vector2(vctr.getX()-25, vctr.getY()+25);
                        ImageView iv = new ImageView(img);
                        iv.setRotate(90);
                        SnapshotParameters params = new SnapshotParameters();
                        params.setFill(Color.TRANSPARENT);
                        Image rotatedImage = iv.snapshot(params, null);
                        sprite = new ObstacleSprite(newPos, 50, 25, Color.TRANSPARENT, rotatedImage, "rectangle", rota);
                        obs = new Obstacle(this, newPos, sprite);
                    } else {
                        vctr = new Vector2(Math.random() * 600, Math.random() * 600);
                        sprite = new ObstacleSprite(vctr, 25, 50, Color.TRANSPARENT, img, "rectangle", rota);
                        obs = new Obstacle(this, vctr, sprite);
                    }
                    break;
                default:
                    img = ImageLoader.loadImage("/car/car1.png", Multi.class);
                    vctr = new Vector2(Math.random()*600, Math.random()*600);
                    sprite = new ObstacleSprite(vctr, 30, 50, Color.TRANSPARENT, img, "rectangle", rota);
                    obs = new Obstacle(this, vctr, sprite);
                    break;
            }
        }
        return obs;
    }

    public List<Obstacle> generate() {
        List<Obstacle> listReturn = new ArrayList<>();
        for(int i = 0; i < 10 ; i++){
            listReturn.add(generateTree());
        }
        return listReturn;
    }

    public void setPlayerCollision(Player player){
        if(player.getBlocked()){
            double x = player.getPosition().getX();
            double y = player.getPosition().getY();
            Vector2 newPosition = new Vector2(x + Randomizer.getRandomIntInRange(-10,10), y + Randomizer.getRandomIntInRange(-10,10));
            player.setPosition(newPosition);
            player.setBlocked(false);
        } else {
            player.reverseSpeed();
            player.getVelocity().mult2(2);
        }
    }

    public void setWeaponCollision(Projectile projectile){}
}
