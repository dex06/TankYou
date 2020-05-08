package fr.unice.miage.plugins.uncompiled;

import fr.unice.miage.common.game_objects.Obstacle;
import fr.unice.miage.common.game_objects.Player;
import fr.unice.miage.common.game_objects.Projectile;
import fr.unice.miage.common.geom.Vector2;
import fr.unice.miage.common.plugins.PlugInObstacle;
import fr.unice.miage.common.sprite.ObstacleSprite;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.List;

public class Grid8x8 implements PlugInObstacle {


    private int generateRandom(int min, int max){
        return (int)(min + Math.random() * ((max - min) + 1));
    }


    public List<Obstacle> generate() {
        List<Obstacle> listReturn = new ArrayList<>();
        for (int i = 0; i < 64 ; i++){
            if((int)(Math.random() * 10) <= 2){
                Vector2 position = new Vector2(600/8*(i%8), 600/8*(i/8));
                double width = 600/8;
                double height = 600/8;
                Paint color = Color.BLUE;
                ObstacleSprite sprite = new ObstacleSprite(position, width, height, color);
                listReturn.add(new Obstacle(this, position, sprite));
            }
        }
        return listReturn;
    }

    public void setPlayerCollision(Player player){
        player.reverseSpeed();
        player.getVelocity().mult2(2);
    }

    public void setWeaponCollision(Projectile projectile){}
}
