package fr.unice.miage.common.game_objects;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.geom.Vector2;
import fr.unice.miage.common.plugins.PlugInObstacle;
import fr.unice.miage.common.sprite.ObstacleSprite;
import fr.unice.miage.common.sprite.Sprite;


public class Obstacle {

    private final PlugInObstacle po;
    private final Vector2 position;
    private final ObstacleSprite sprite;



    public Obstacle(PlugInObstacle po, Vector2 position, ObstacleSprite sprite) {
        this.po = po;
        this.position = position;
        this.sprite = sprite;
    }

    public void draw(CanvasGUI canvas){
        sprite.draw(canvas);
    }

    public Vector2 getPosition() {
        return position;
    }


    public Sprite getSprite() { return sprite; }

    public void setPlayerCollision(Player player) {
        po.setPlayerCollision(player);
    }

    public void setWeaponCollision(Projectile projectile){}

}
