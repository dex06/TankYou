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
import java.util.Vector;

public class Predef implements PlugInObstacle {


    private Image makeImg(String path){
        Image img;
//        try {
//            img = new Image(Multi.class.getClassLoader().getResourceAsStream(path));
//        } catch (Exception e){
//            img = new Image(path);
//        }

//        System.out.println(path);
        img = ImageLoader.loadImage(path, Predef.class);

//        ImageLoader.
        return img;
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

//        for(int i = 0; i < 10 ; i++){
//            listReturn.add(generateTree());
//        }

        Image img;
        Vector2 vctr;
        ObstacleSprite sprite;
        Obstacle obs;

        //VOITURE
        img = makeImg("/car/car1.png");

        vctr = new Vector2(20, 150);
        sprite = new ObstacleSprite(vctr, 30, 50, Color.BLACK, img, "rectangle", -40);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        vctr = new Vector2(40, 130);
        sprite = new ObstacleSprite(vctr, 30, 50, Color.BLACK, img, "rectangle", -40);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        vctr = new Vector2(90, 410);
        sprite = new ObstacleSprite(vctr, 30, 50, Color.BLACK, img, "rectangle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        vctr = new Vector2(390, 410);
        sprite = new ObstacleSprite(vctr, 30, 50, Color.BLACK, img, "rectangle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        vctr = new Vector2(425, 410);
        sprite = new ObstacleSprite(vctr, 30, 50, Color.BLACK, img, "rectangle", 180);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);



        img = makeImg("/car/car2.png");

        vctr = new Vector2(60, 110);
        sprite = new ObstacleSprite(vctr, 30, 50, Color.BLACK, img, "rectangle", -40);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        vctr = new Vector2(125, 410);
        sprite = new ObstacleSprite(vctr, 30, 50, Color.BLACK, img, "rectangle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        vctr = new Vector2(190, 410);
        sprite = new ObstacleSprite(vctr, 30, 50, Color.BLACK, img, "rectangle", 180);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        vctr = new Vector2(460, 410);
        sprite = new ObstacleSprite(vctr, 30, 50, Color.BLACK, img, "rectangle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        vctr = new Vector2(420, 200);
        sprite = new ObstacleSprite(vctr, 30, 50, Color.BLACK, img, "rectangle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);




//        img = makeImg("/car/car2.png");
//
//        vctr = new Vector2(80, 110);
//        sprite = new ObstacleSprite(vctr, 30, 50, Color.BLACK, img, "rectangle", -40);
//        obs = new Obstacle(this, vctr, sprite);
//        listReturn.add(obs);

        img = makeImg("/mur/mur2.png");
        vctr = new Vector2(50, 130);
        sprite = new ObstacleSprite(vctr, 20, 100, Color.BLACK, img, "rectangle", 50);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);



        //MUR
        img = makeImg("/mur/mur2.png");
        vctr = new Vector2(100, 350);
        sprite = new ObstacleSprite(vctr, 20, 100, Color.BLACK, img, "rectangle", 90);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);
        vctr = new Vector2(205, 350);
        sprite = new ObstacleSprite(vctr, 20, 100, Color.BLACK, img, "rectangle", 90);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);


        vctr = new Vector2(400, 350);
        sprite = new ObstacleSprite(vctr, 20, 100, Color.BLACK, img, "rectangle", 90);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);
        vctr = new Vector2(505, 350);
        sprite = new ObstacleSprite(vctr, 20, 100, Color.BLACK, img, "rectangle", 90);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        img = makeImg("/mur/mur1.png");
        vctr = new Vector2(270, 50);
        sprite = new ObstacleSprite(vctr, 100, 20, Color.BLACK, img, "rectangle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);
        vctr = new Vector2(320, 50);
        sprite = new ObstacleSprite(vctr, 100, 20, Color.BLACK, img, "rectangle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);


        //ARBRE

        img = makeImg("/arbre/arbre" + ((int)(Math.random()*3) + 1 )+ ".png");

        vctr = new Vector2(60, 120);
        sprite = new ObstacleSprite(vctr, 50, 50, Color.BLACK, img, "circle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        img = makeImg("/arbre/arbre" + (int)(Math.random()*4)+ ".png");

        vctr = new Vector2(25, 375);
        sprite = new ObstacleSprite(vctr, 50, 50, Color.BLACK, img, "circle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        img = makeImg("/arbre/arbre" + ((int)(Math.random()*3) + 1 )+ ".png");

        vctr = new Vector2(530, 375);
        sprite = new ObstacleSprite(vctr, 50, 50, Color.BLACK, img, "circle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        img = makeImg("/arbre/arbre" + ((int)(Math.random()*3) + 1 )+ ".png");

        vctr = new Vector2(450, 200);
        sprite = new ObstacleSprite(vctr, 50, 50, Color.BLACK, img, "circle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        img = makeImg("/arbre/arbre" + ((int)(Math.random()*3) + 1 )+ ".png");

        vctr = new Vector2(410, 225);
        sprite = new ObstacleSprite(vctr, 50, 50, Color.BLACK, img, "circle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        img = makeImg("/arbre/arbre" + ((int)(Math.random()*3) + 1 )+ ".png");

        vctr = new Vector2(430, 215);
        sprite = new ObstacleSprite(vctr, 50, 50, Color.BLACK, img, "circle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        img = makeImg("/arbre/arbre" + ((int)(Math.random()*3) + 1 )+ ".png");

        vctr = new Vector2(250, 370);
        sprite = new ObstacleSprite(vctr, 50, 50, Color.BLACK, img, "circle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        img = makeImg("/arbre/arbre" + ((int)(Math.random()*3) + 1 )+ ".png");

        vctr = new Vector2(250, 420);
        sprite = new ObstacleSprite(vctr, 50, 50, Color.BLACK, img, "circle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        img = makeImg("/arbre/arbre" + ((int)(Math.random()*3) + 1 )+ ".png");

        vctr = new Vector2(250, 320);
        sprite = new ObstacleSprite(vctr, 50, 50, Color.BLACK, img, "circle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        img = makeImg("/arbre/arbre" + ((int)(Math.random()*3) + 1 )+ ".png");

        vctr = new Vector2(350, 370);
        sprite = new ObstacleSprite(vctr, 50, 50, Color.BLACK, img, "circle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        img = makeImg("/arbre/arbre" + ((int)(Math.random()*3) + 1 )+ ".png");

        vctr = new Vector2(350, 420);
        sprite = new ObstacleSprite(vctr, 50, 50, Color.BLACK, img, "circle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        img = makeImg("/arbre/arbre" + ((int)(Math.random()*3) + 1 )+ ".png");

        vctr = new Vector2(350, 320);
        sprite = new ObstacleSprite(vctr, 50, 50, Color.BLACK, img, "circle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        img = makeImg("/arbre/arbre" + ((int)(Math.random()*3) + 1 )+ ".png");

        vctr = new Vector2(320, 50);
        sprite = new ObstacleSprite(vctr, 50, 50, Color.BLACK, img, "circle", 0);
        obs = new Obstacle(this, vctr, sprite);
        listReturn.add(obs);

        img = makeImg("/arbre/arbre" + ((int)(Math.random()*3) + 1 )+ ".png");

        vctr = new Vector2(280, 30);
        sprite = new ObstacleSprite(vctr, 50, 50, Color.BLACK, img, "circle", 0);
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
