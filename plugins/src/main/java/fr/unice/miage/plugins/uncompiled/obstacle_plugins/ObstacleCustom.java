package fr.unice.miage.plugins.uncompiled.obstacle_plugins;

import fr.unice.miage.common.game_objects.Obstacle;
import fr.unice.miage.common.game_objects.Player;
import fr.unice.miage.common.game_objects.Projectile;
import fr.unice.miage.common.geom.Vector2;
import fr.unice.miage.common.plugins.PlugInObstacle;
import fr.unice.miage.common.sprite.ObstacleSprite;
import fr.unice.miage.common.utils.Randomizer;
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
            ObstacleSprite sprite = new ObstacleSprite(position, width, height, color, "rectangle");
            listReturn.add(new Obstacle(this, position, sprite));
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

    public void setWeaponCollision(Projectile projectile){

    }
}
