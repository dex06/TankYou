package fr.unice.miage.plugins.uncompiled.obstacle_plugins;

import fr.unice.miage.common.game_objects.Obstacle;
import fr.unice.miage.common.game_objects.Player;
import fr.unice.miage.common.game_objects.Projectile;
import fr.unice.miage.common.geom.Vector2;
import fr.unice.miage.common.plugins.PlugInObstacle;
import fr.unice.miage.common.sprite.ObstacleSprite;
import fr.unice.miage.common.utils.ImageLoader;
import fr.unice.miage.common.utils.Randomizer;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Predef implements PlugInObstacle {


    private Image makeImg(String path){
        Image img = ImageLoader.loadImage(path, Predef.class);
        return img;
    }

    public List<Obstacle> generate() {
        List<Obstacle> listReturn = new ArrayList<>();

        Image img;
        Vector2 vctr;
        ObstacleSprite sprite;
        Obstacle obs;

        //VOITURE
        img = makeImg("/car/car1.png");

        vctr = new Vector2(20, 150);
        sprite = new ObstacleSprite(vctr, 30, 50, Color.TRANSPARENT, img, "rectangle", -40);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        vctr = new Vector2(40, 130);
        sprite = new ObstacleSprite(vctr, 30, 50, Color.TRANSPARENT, img, "rectangle", -40);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        vctr = new Vector2(90, 410);
        sprite = new ObstacleSprite(vctr, 30, 50, Color.TRANSPARENT, img, "rectangle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        vctr = new Vector2(390, 410);
        sprite = new ObstacleSprite(vctr, 30, 50, Color.TRANSPARENT, img, "rectangle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        vctr = new Vector2(425, 410);
        sprite = new ObstacleSprite(vctr, 30, 50, Color.TRANSPARENT, img, "rectangle", 180);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);



        img = makeImg("/car/car2.png");

        vctr = new Vector2(60, 110);
        sprite = new ObstacleSprite(vctr, 30, 50, Color.TRANSPARENT, img, "rectangle", -40);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        vctr = new Vector2(125, 410);
        sprite = new ObstacleSprite(vctr, 30, 50, Color.TRANSPARENT, img, "rectangle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        vctr = new Vector2(190, 410);
        sprite = new ObstacleSprite(vctr, 30, 50, Color.TRANSPARENT, img, "rectangle", 180);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        vctr = new Vector2(460, 410);
        sprite = new ObstacleSprite(vctr, 30, 50, Color.TRANSPARENT, img, "rectangle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        vctr = new Vector2(420, 200);
        sprite = new ObstacleSprite(vctr, 30, 50, Color.TRANSPARENT, img, "rectangle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);




//        img = makeImg("/car/car2.png");
//
//        vctr = new Vector2(80, 110);
//        sprite = new ObstacleSprite(vctr, 30, 50, Color.TRANSPARENT, img, "rectangle", -40);
//        obs = new Obstacle(this, vctr, sprite);
//        listReturn.add(obs);

        img = makeImg("/mur/mur1.png");
        vctr = new Vector2(50, 130);
        sprite = new ObstacleSprite(vctr, 100, 20, Color.TRANSPARENT, img, "rectangle", 50);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);



        //MUR
        img = makeImg("/mur/mur1.png");
        vctr = new Vector2(70, 390);
        sprite = new ObstacleSprite(vctr, 100, 20, Color.TRANSPARENT, img, "rectangle", 90);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);
        vctr = new Vector2(175, 390);
        sprite = new ObstacleSprite(vctr, 100, 20, Color.TRANSPARENT, img, "rectangle", 90);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);


        vctr = new Vector2(370, 390);
        sprite = new ObstacleSprite(vctr, 100, 20, Color.TRANSPARENT, img, "rectangle", 90);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);
        vctr = new Vector2(475, 390);
        sprite = new ObstacleSprite(vctr, 100, 20, Color.TRANSPARENT, img, "rectangle", 90);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        img = makeImg("/mur/mur1.png");
        vctr = new Vector2(270, 50);
        sprite = new ObstacleSprite(vctr, 100, 20, Color.TRANSPARENT, img, "rectangle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);
        vctr = new Vector2(320, 50);
        sprite = new ObstacleSprite(vctr, 100, 20, Color.TRANSPARENT, img, "rectangle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);


        //ARBRE

        img = makeImg("/arbre/arbre" + ((int)(Math.random()*3) + 1 )+ ".png");

        vctr = new Vector2(60, 120);
        sprite = new ObstacleSprite(vctr, 50, 50, Color.TRANSPARENT, img, "circle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        img = makeImg("/arbre/arbre" + (int)(Math.random()*3)+ ".png");

        vctr = new Vector2(25, 375);
        sprite = new ObstacleSprite(vctr, 50, 50, Color.TRANSPARENT, img, "circle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        img = makeImg("/arbre/arbre" + ((int)(Math.random()*3) + 1 )+ ".png");

        vctr = new Vector2(530, 375);
        sprite = new ObstacleSprite(vctr, 50, 50, Color.TRANSPARENT, img, "circle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        img = makeImg("/arbre/arbre" + ((int)(Math.random()*3) + 1 )+ ".png");

        vctr = new Vector2(450, 200);
        sprite = new ObstacleSprite(vctr, 50, 50, Color.TRANSPARENT, img, "circle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        img = makeImg("/arbre/arbre" + ((int)(Math.random()*3) + 1 )+ ".png");

        vctr = new Vector2(410, 225);
        sprite = new ObstacleSprite(vctr, 50, 50, Color.TRANSPARENT, img, "circle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        img = makeImg("/arbre/arbre" + ((int)(Math.random()*3) + 1 )+ ".png");

        vctr = new Vector2(430, 215);
        sprite = new ObstacleSprite(vctr, 50, 50, Color.TRANSPARENT, img, "circle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        img = makeImg("/arbre/arbre" + ((int)(Math.random()*3) + 1 )+ ".png");

        vctr = new Vector2(250, 370);
        sprite = new ObstacleSprite(vctr, 50, 50, Color.TRANSPARENT, img, "circle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        img = makeImg("/arbre/arbre" + ((int)(Math.random()*3) + 1 )+ ".png");

        vctr = new Vector2(250, 420);
        sprite = new ObstacleSprite(vctr, 50, 50, Color.TRANSPARENT, img, "circle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        img = makeImg("/arbre/arbre" + ((int)(Math.random()*3) + 1 )+ ".png");

        vctr = new Vector2(250, 320);
        sprite = new ObstacleSprite(vctr, 50, 50, Color.TRANSPARENT, img, "circle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        img = makeImg("/arbre/arbre" + ((int)(Math.random()*3) + 1 )+ ".png");

        vctr = new Vector2(350, 370);
        sprite = new ObstacleSprite(vctr, 50, 50, Color.TRANSPARENT, img, "circle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        img = makeImg("/arbre/arbre" + ((int)(Math.random()*3) + 1 )+ ".png");

        vctr = new Vector2(350, 420);
        sprite = new ObstacleSprite(vctr, 50, 50, Color.TRANSPARENT, img, "circle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        img = makeImg("/arbre/arbre" + ((int)(Math.random()*3) + 1 )+ ".png");

        vctr = new Vector2(350, 320);
        sprite = new ObstacleSprite(vctr, 50, 50, Color.TRANSPARENT, img, "circle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        img = makeImg("/arbre/arbre" + ((int)(Math.random()*3) + 1 )+ ".png");

        vctr = new Vector2(320, 50);
        sprite = new ObstacleSprite(vctr, 50, 50, Color.TRANSPARENT, img, "circle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        img = makeImg("/arbre/arbre" + ((int)(Math.random()*3) + 1 )+ ".png");

        vctr = new Vector2(280, 30);
        sprite = new ObstacleSprite(vctr, 50, 50, Color.TRANSPARENT, img, "circle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);


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
