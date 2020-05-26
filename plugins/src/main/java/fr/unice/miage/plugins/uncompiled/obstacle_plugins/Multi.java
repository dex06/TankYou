package fr.unice.miage.plugins.uncompiled.obstacle_plugins;

import fr.unice.miage.common.game_objects.Obstacle;
import fr.unice.miage.common.game_objects.Player;
import fr.unice.miage.common.game_objects.Projectile;
import fr.unice.miage.common.geom.Vector2;
import fr.unice.miage.common.plugins.PlugInObstacle;
import fr.unice.miage.common.sprite.ObstacleSprite;
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
        System.out.println("Object : " + rdmObject + ", rota : " + rota);
        if(rdmObject == 1){
            int rdm = 1 + (int)(Math.random()*4);
            switch (rdm){
                case 0:
                    try {
                        img = new Image(Arbre.class.getClassLoader().getResourceAsStream("/arbre/arbre1.png"));
                    } catch(Exception e){
                        img = new Image("/arbre/arbre1.png");
                    }
                    break;
                case 1:
                    try {
                        img = new Image(Arbre.class.getClassLoader().getResourceAsStream("/arbre/arbre2.png"));
                    } catch(Exception e){
                        img = new Image("/arbre/arbre2.png");
                    }            break;
                case 3:
                    try {
                        img = new Image(Arbre.class.getClassLoader().getResourceAsStream("/arbre/arbre3.png"));
                    } catch(Exception e){
                        img = new Image("/arbre/arbre3.png");
                    }            break;
                case 4:
                    try {
                        img = new Image(Arbre.class.getClassLoader().getResourceAsStream("/arbre/arbre4.png"));
                    } catch(Exception e){
                        img = new Image("/arbre/arbre4.png");
                    }            break;
                default:
                    try {
                        img = new Image(Arbre.class.getClassLoader().getResourceAsStream("/arbre/arbre1.png"));
                    } catch(Exception e){
                        img = new Image("/arbre/arbre1.png");
                    }            break;
            }
            Vector2 vctr = new Vector2(Math.random()*600, Math.random()*600);
            ObstacleSprite sprite = new ObstacleSprite(vctr, 50, 50, Color.BLACK, img, "circle", rota);
            obs = new Obstacle(this, vctr, sprite);

        }
        else if(rdmObject == 2){
            try {
                img = new Image(Multi.class.getClassLoader().getResourceAsStream("/mur/mur1.png"));
            } catch (Exception e){
                img = new Image("/mur/mur1.png");
            }
            Vector2 vctr = new Vector2(Math.random()*600, Math.random()*600);
            ObstacleSprite sprite = new ObstacleSprite(vctr, 150, 25, Color.BLACK, img, "rectangle", rota);
            obs = new Obstacle(this, vctr, sprite);
        }
        else if(rdmObject == 3){
            try {
                img = new Image(Multi.class.getClassLoader().getResourceAsStream("/mur/mur2.png"));
            } catch (Exception e){
                img = new Image("/mur/mur2.png");
            }
            Vector2 vctr = new Vector2(Math.random()*600, Math.random()*600);
            ObstacleSprite sprite = new ObstacleSprite(vctr, 36, 150, Color.BLACK, img, "rectangle", rota);
            obs = new Obstacle(this, vctr, sprite);
        }
        else if(rdmObject == 4){
            int rdm = 1 + (int)(Math.random()*2);
            Vector2 vctr = null;
            ObstacleSprite sprite = null;
            switch (rdm){
                case 1:
                    try {
                        img = new Image(Multi.class.getClassLoader().getResourceAsStream("/car/car1.png"));
                    } catch (Exception e){
                        img = new Image("/car/car1.png");
                    }
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
                        sprite = new ObstacleSprite(vctr, 30, 50, Color.BLACK, img, "rectangle");
                        obs = new Obstacle(this, vctr, sprite);
                    }
                    break;
                case 2:
                    try {
                        img = new Image(Multi.class.getClassLoader().getResourceAsStream("/car/car2.png"));
                    } catch (Exception e){
                        img = new Image("/car/car2.png");
                    }
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
                        sprite = new ObstacleSprite(vctr, 25, 50, Color.BLACK, img, "rectangle", rota);
                        obs = new Obstacle(this, vctr, sprite);
                    }
                    break;
                default:
                    try {
                        img = new Image(Multi.class.getClassLoader().getResourceAsStream("/car/car1.png"));
                    } catch (Exception e){
                        img = new Image("/car/car1.png");
                    }
                    vctr = new Vector2(Math.random()*600, Math.random()*600);
                    sprite = new ObstacleSprite(vctr, 30, 50, Color.BLACK, img, "rectangle", rota);
                    obs = new Obstacle(this, vctr, sprite);
                    break;
            }
        }

        return obs;
    }

    public List<Obstacle> generate() {
        List<Obstacle> listReturn = new ArrayList<>();
//        ObstacleSprite sprite = new ObstacleSprite(new Vector2(100, 100), 50, 50, Color.BLACK, new Image(Arbre.class.getClassLoader().getResourceAsStream("/arbre/arbre1.png")));
//        Obstacle obs = new Obstacle(this, new Vector2(100, 100), sprite);
//
//        ObstacleSprite sprite2 = new ObstacleSprite(new Vector2(200, 200), 50, 50, Color.BLACK, new Image(Arbre.class.getClassLoader().getResourceAsStream("/arbre/arbre1.png")));
//        Obstacle obs2 = new Obstacle(this, new Vector2(200, 200), sprite2);
//
//        ObstacleSprite sprite3 = new ObstacleSprite(new Vector2(300, 300), 50, 50, Color.BLACK, new Image(Arbre.class.getClassLoader().getResourceAsStream("/arbre/arbre1.png")));
//        Obstacle obs3 = new Obstacle(this, new Vector2(300, 300), sprite3);

        for(int i = 0; i < 10 ; i++){
            listReturn.add(generateTree());
        }

//        listReturn.add(obs);
//        listReturn.add(obs2);
//        listReturn.add(obs3);

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
