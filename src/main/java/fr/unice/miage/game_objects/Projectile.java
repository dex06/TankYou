package fr.unice.miage.game_objects;

import fr.unice.miage.sprite.Sprite;

public class Projectile {
    Sprite projectileSprite;
    String projectileName;

    public Projectile(Sprite projectileSprite, String projectileName){
        this.projectileSprite = projectileSprite;
        this.projectileName = projectileName;
    }

    public Sprite getSprite(){
        return this.projectileSprite;
    }

    public String getName(){ return this.projectileName; }

    public void collidedWith(Projectile projectile) {

    }
}
