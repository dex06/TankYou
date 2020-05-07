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

public class ObstacleCustom implements PlugInObstacle {

    public List<Obstacle> generate() {
        List<Obstacle> listReturn = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Vector2 position = new Vector2(10 + (int) (Math.random() * ((600 - 10) + 1)), 10 + (int) (Math.random() * ((600 - 10) + 1)));
            double width = 10 + (int) (Math.random() * ((70 - 10) + 1));
            double height = 10 + (int) (Math.random() * ((70 - 10) + 1));
            Paint color = Color.RED;
            ObstacleSprite sprite = new ObstacleSprite(position, width, height, color);
            listReturn.add(new Obstacle(this, position, sprite));
        }
        return listReturn;
    }

    public void setPlayerCollision(Player player){
        player.reverseSpeed();
    }

    public void setWeaponCollision(Projectile projectile){

    }
}
