package fr.unice.miage.plugins.uncompiled.weapon_plugins;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.game_objects.Player;
import fr.unice.miage.common.game_objects.Projectile;
import fr.unice.miage.common.geom.Vector2;
import fr.unice.miage.common.input.Mouse;
import fr.unice.miage.common.plugins.PlugInWeapon;
import fr.unice.miage.common.sprite.RectangleSprite;
import fr.unice.miage.common.sprite.Sprite;
import fr.unice.miage.common.utils.Finder;
import fr.unice.miage.common.utils.Timer;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Mine2 implements PlugInWeapon {

    public void moveProjectile(Projectile projectile){
        if(Timer.getChrono() - projectile.getShotTime() >= 10) projectile.setHit();

    }

    public void draw(CanvasGUI canvas, Projectile projectile) {
        Image img = getSprite(projectile, Timer.getChrono());
        canvas.getGraphicsContext().drawImage(img, projectile.getX()-30, projectile.getY()-30, 60, 60);
    }

    public void onProjectileOut(String axis, Projectile projectile){
        projectile.endProjectile();

    }

    public void shoot(Player player) {
        if(Timer.getChrono() - player.getLastShot() >= 3){

            Vector2 direction;
            if(Mouse.isMouseOn()){
                direction = Mouse.getLastShootingPosition().sub2(player.getPosition()).norm2();
            } else {
                Player p = Finder.findClosestPlayer(player);
                direction = p.getPosition().sub2(player.getPosition()).norm2();  //Vecteur normalisé
            }

            double xCenter = player.getX() + player.getSprite().getWidth()/2;
            double yCenter = player.getY() + player.getSprite().getHeight()/2;
            Vector2 position = new Vector2(xCenter, yCenter);
            Vector2 velocity = new Vector2(5 * direction.getX(), 5 * direction.getY());
            Sprite sprite = createSprite(player);
            player.addProjectile(new Projectile(this, player, position, velocity, sprite, Timer.getChrono(), "rebondissante"));
            player.setLastShot(Timer.getChrono());
            player.incrementNumberOfShots();
        }
    }

    public Sprite createSprite(Player player){
        return new RectangleSprite(player,30,30, Color.FLORALWHITE, false);
    }

    public void applyPlayerImpact(Player player){
        player.setHealth(player.getHealth()-30);
    }

    public void applyObstacleCollision(Projectile projectile, String inversion){
        projectile.setHitTime(Timer.getChrono());
        projectile.setHit();
    }

    private Image getSprite(Projectile projectile, double chrono){
        double currentTime = chrono;
        Image explosionImg= new Image(Mine2.class.getClassLoader().getResourceAsStream("/mineExplosions/Bomb_Explosion_A_000.png"));
        if(!projectile.hasEnded() & !projectile.hasHit()) {
            explosionImg= new Image(Mine2.class.getClassLoader().getResourceAsStream("/mineExplosions/Bomb_Explosion_A_000.png"));
            if(Math.round(Timer.getChrono()) % 2 == 0){
                explosionImg = new Image(Mine2.class.getClassLoader().getResourceAsStream("/mineExplosions/Bomb_Explosion_A_001.png"));
            }
        }
        if(!projectile.hasEnded() & projectile.hasHit()) {
            double hitTime = projectile.getHitTime();
            double difTime = currentTime - hitTime;
            if (difTime <= 0.4) {
                explosionImg = new Image(Mine2.class.getClassLoader().getResourceAsStream("/mineExplosions/Bomb_Explosion_A_001.png"));
            }
            if (difTime > 0.4 & difTime <= 0.8) {
                explosionImg = new Image(Mine2.class.getClassLoader().getResourceAsStream("/mineExplosions/Bomb_Explosion_A_002.png"));
            }
            if (difTime > 0.8 & difTime <= 1.2) {
                explosionImg = new Image(Mine2.class.getClassLoader().getResourceAsStream("/mineExplosions/Bomb_Explosion_A_003.png"));
            }
            if (difTime > 1.2 & difTime <= 1.6) {
                explosionImg = new Image(Mine2.class.getClassLoader().getResourceAsStream("/mineExplosions/Bomb_Explosion_A_004.png"));
            }
            if (difTime > 2 & difTime <= 2.4) {
                explosionImg = new Image(Mine2.class.getClassLoader().getResourceAsStream("/mineExplosions/Bomb_Explosion_A_005.png"));
            }
            if (difTime > 2.4 & difTime <= 2.8) {
                explosionImg = new Image(Mine2.class.getClassLoader().getResourceAsStream("/mineExplosions/Bomb_Explosion_A_006.png"));
            }
            if (difTime > 3.2 & difTime <= 3.6) {
                explosionImg = new Image(Mine2.class.getClassLoader().getResourceAsStream("/mineExplosions/Bomb_Explosion_A_007.png"));
            }
            if (difTime > 4) {
                explosionImg = new Image(Mine2.class.getClassLoader().getResourceAsStream("/mineExplosions/Bomb_Explosion_A_008.png"));
                projectile.endProjectile();
            }
        }
        return explosionImg;
    }

}

