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

public class ObstacleCustom2 implements PlugInObstacle {


    private int generateRandom(int min, int max){
        return (int)(min + Math.random() * ((max - min) + 1));
    }

    private List<Obstacle> generateTShape() {
        List<Obstacle> listReturnT = new ArrayList<>();

        Vector2 position = new Vector2(generateRandom(10, 300), generateRandom(10, 300));
        double width = 100;
        double height = 40;
        Paint color = Color.BLACK;
        ObstacleSprite sprite = new ObstacleSprite(position, width, height, color);
        Obstacle barre = new Obstacle(this, position, sprite);

        Vector2 position2 = new Vector2(position.getX() + width/3, position.getY() + height);
        double width2 = width/3;
        double height2 = 60;
        ObstacleSprite sprite2 = new ObstacleSprite(position2, width2, height2, color);
        Obstacle barre2 = new Obstacle(this, position2, sprite2);

        listReturnT.add(barre);
        listReturnT.add(barre2);

        return listReturnT;
    }
    private List<Obstacle> generateLShape(){
        return null;
    }
    private List<Obstacle> generateUShape(){
        return null;
    }


    public List<Obstacle> generate() {
        List<Obstacle> listReturn = new ArrayList<>();
        for (int i = 0; i < 10 ; i++){
            //int forme = (int)(Math.random() * (2));
            int forme = 1;
            // 1 - T, 2 - U, 3 - L
            switch (forme){
                case 1:
                    listReturn.addAll(generateTShape());
                    break;
                case 2:
                    listReturn.addAll(generateUShape());
                    break;
                case 3:
                    listReturn.addAll(generateLShape());
                    break;
            }
        }
        return listReturn;
    }

    public void setPlayerCollision(Player player){
        player.reverseSpeed();
    }

    public void setWeaponCollision(Projectile projectile){}
}
