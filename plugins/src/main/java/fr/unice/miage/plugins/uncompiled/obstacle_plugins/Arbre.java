package fr.unice.miage.plugins.uncompiled.obstacle_plugins;

import fr.unice.miage.common.game_objects.Obstacle;
import fr.unice.miage.common.game_objects.Player;
import fr.unice.miage.common.game_objects.Projectile;
import fr.unice.miage.common.geom.Vector2;
import fr.unice.miage.common.plugins.PlugInObstacle;
import fr.unice.miage.common.sprite.ObstacleSprite;
import fr.unice.miage.common.utils.Randomizer;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Arbre implements PlugInObstacle {

    private Obstacle generateTree(){
        int rdm = (int)(Math.random()*4);
        Image img;
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
        ObstacleSprite sprite = new ObstacleSprite(vctr, 50, 50, Color.BLACK, img, "circle");
        Obstacle obs = new Obstacle(this, vctr, sprite);
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
