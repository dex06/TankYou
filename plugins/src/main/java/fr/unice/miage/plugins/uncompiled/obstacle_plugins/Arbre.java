package fr.unice.miage.plugins.uncompiled.obstacle_plugins;

import fr.unice.miage.common.game_objects.Obstacle;
import fr.unice.miage.common.game_objects.Player;
import fr.unice.miage.common.game_objects.Projectile;
import fr.unice.miage.common.geom.Vector2;
import fr.unice.miage.common.plugins.PlugInObstacle;
import fr.unice.miage.common.sprite.ObstacleSprite;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.List;

public class Arbre implements PlugInObstacle {

    private Obstacle generateTree(){
        int rdm = (int)(Math.random()*4);
        Image img;
        switch (rdm){
            case 0: img = new Image(Arbre.class.getClassLoader().getResourceAsStream("/arbre/arbre1.png"));
            break;
            case 1: img = new Image(Arbre.class.getClassLoader().getResourceAsStream("/arbre/arbre2.png"));
            break;
            case 3: img = new Image(Arbre.class.getClassLoader().getResourceAsStream("/arbre/arbre3.png"));
            break;
            case 4: img = new Image(Arbre.class.getClassLoader().getResourceAsStream("/arbre/arbre4.png"));
            break;
            default: img = new Image(Arbre.class.getClassLoader().getResourceAsStream("/arbre/arbre1.png"));
            break;


        }
        Vector2 vctr = new Vector2(Math.random()*600, Math.random()*600);
        ObstacleSprite sprite = new ObstacleSprite(vctr, 50, 50, Color.BLACK, img);
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
        player.reverseSpeed();
        player.getVelocity().mult2(2);
    }

    public void setWeaponCollision(Projectile projectile){}
}
