package fr.unice.miage.plugins.uncompiled.weapon_plugins;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.game_objects.Player;
import fr.unice.miage.common.game_objects.Projectile;
import fr.unice.miage.common.geom.Rotation;
import fr.unice.miage.common.geom.Vector2;
import fr.unice.miage.common.input.Mouse;
import fr.unice.miage.common.plugins.PlugInWeapon;
import fr.unice.miage.common.sprite.RectangleSprite;
import fr.unice.miage.common.sprite.Sprite;
import fr.unice.miage.common.utils.Finder;
import fr.unice.miage.common.utils.Timer;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class ObusH implements PlugInWeapon {


    public void moveProjectile(Projectile projectile){
        if(Timer.getChrono() - projectile.getShotTime() >= 5) projectile.setHit();
        if(!projectile.hasEnded() & !projectile.hasHit()) projectile.addPosition(projectile.getVelocity());

    }

    public void draw(CanvasGUI canvas, Projectile projectile) {
        Image img = getSprite(projectile, Timer.getChrono());
        //canvas.getGraphicsContext().drawImage(img, projectile.getX()-30, projectile.getY()-30, 60, 60);
        double rot = projectile.getRotation();

        double x = projectile.getX();
        double y = projectile.getY();
        double w = 60;
        double h = 45;
        ImageView iv = new ImageView(img);
        iv.setFitWidth(w);
        iv.setFitHeight(h);
        iv.setRotate(rot);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        Image rotatedImage = iv.snapshot(params, null);
        GraphicsContext c= canvas.getGraphicsContext();
        c.save();
        c.drawImage(rotatedImage, x - w/2, y - h/2);
        c.restore();
    }

    public void onProjectileOut(String axis, Projectile projectile){
        projectile.endProjectile();

    }

    public void shoot(Player player) {
        if(Timer.getChrono() - player.getLastShot() > 3){
            Vector2 direction;
            if(Mouse.isMouseOn()){
                direction = Mouse.getLastShootingPosition().sub2(player.getPosition()).norm2();
            } else {
                Player p = Finder.findClosestPlayer(player);
                direction = p.getPosition().sub2(player.getPosition()).norm2();  //Vecteur normalisé
            }

            Vector2 velocity = new Vector2(5 * direction.getX(), 5 * direction.getY());

            Sprite sprite = createSprite(player);
            Projectile newPrj = new Projectile(this, player, player.getPosition(), velocity, sprite, Timer.getChrono(), "rebondissante");
            double wRot = Rotation.rotation2Vectors(new Vector2(0,-1), direction);
            newPrj.setRotation(wRot);
            player.setWeaponRotation(wRot);
            player.addProjectile(newPrj);
            player.setLastShot(Timer.getChrono());
            player.incrementNumberOfShots();
        }
    }

    public Sprite createSprite(Player player){
        return new RectangleSprite(player,30,30, Color.FLORALWHITE, false);
    }

    public void applyPlayerImpact(Projectile projectile, Player player){
        player.setHealth(player.getHealth()-10);
        projectile.setHitTime(Timer.getChrono());
        projectile.setHit();
    }

    public void applyProjectileImpact(Projectile projectile){
        projectile.setHitTime(Timer.getChrono());
        projectile.setHit();
    }

    public void applyObstacleCollision(Projectile projectile, String inversion){
        projectile.setHitTime(Timer.getChrono());
        projectile.setHit();
    }

    private Image getSprite(Projectile projectile, double chrono){
        double currentTime = chrono;
        Image explosionImg;
        try {
            explosionImg = new Image(ObusL.class.getClassLoader().getResourceAsStream("/shells/Heavy_Shell.png"));
        } catch (Exception e){
            explosionImg = new Image("/shells/Heavy_Shell.png");
        }
        if(!projectile.hasEnded() & !projectile.hasHit()) {
            try {
                explosionImg = new Image(ObusL.class.getClassLoader().getResourceAsStream("/shells/Heavy_Shell.png"));
            } catch (Exception e){
                explosionImg = new Image("/shells/Heavy_Shell.png");
            }
        }
        if(!projectile.hasEnded() & projectile.hasHit()) {
            double hitTime = projectile.getHitTime();
            double difTime = currentTime - hitTime;
            if (difTime <= 0.25) {
                try {
                    explosionImg = new Image(ObusL.class.getClassLoader().getResourceAsStream("/shellExplosions/Explosion_A.png"));
                } catch(Exception e){
                    explosionImg = new Image("/shellExplosions/Explosion_A.png");
                }
            }
            if (difTime > 0.25 & difTime <= 0.5) {
                try {
                    explosionImg = new Image(ObusL.class.getClassLoader().getResourceAsStream("/shellExplosions/Explosion_B.png"));
                } catch(Exception e){
                    explosionImg = new Image("/shellExplosions/Explosion_B.png");
                }            }
            if (difTime > 0.5 & difTime <= 0.75) {
                try {
                    explosionImg = new Image(ObusL.class.getClassLoader().getResourceAsStream("/shellExplosions/Explosion_C.png"));
                } catch(Exception e){
                    explosionImg = new Image("/shellExplosions/Explosion_C.png");
                }            }
            if (difTime > 0.75 & difTime <= 1) {
                try {
                    explosionImg = new Image(ObusL.class.getClassLoader().getResourceAsStream("/shellExplosions/Explosion_D.png"));
                } catch(Exception e){
                    explosionImg = new Image("/shellExplosions/Explosion_D.png");
                }            }
            if (difTime > 1 & difTime <= 1.25) {
                try {
                    explosionImg = new Image(ObusL.class.getClassLoader().getResourceAsStream("/shellExplosions/Explosion_E.png"));
                } catch(Exception e){
                    explosionImg = new Image("/shellExplosions/Explosion_E.png");
                }            }
            if (difTime > 1.25 & difTime <= 1.5) {
                try {
                    explosionImg = new Image(ObusL.class.getClassLoader().getResourceAsStream("/shellExplosions/Explosion_F.png"));
                } catch(Exception e){
                    explosionImg = new Image("/shellExplosions/Explosion_F.png");
                }            }
            if (difTime > 1.5 & difTime <= 1.75) {
                try {
                    explosionImg = new Image(ObusL.class.getClassLoader().getResourceAsStream("/shellExplosions/Explosion_G.png"));
                } catch(Exception e){
                    explosionImg = new Image("/shellExplosions/Explosion_G.png");
                }            }
            if (difTime > 1.75 & difTime <= 2) {
                try {
                    explosionImg = new Image(ObusL.class.getClassLoader().getResourceAsStream("/shellExplosions/Explosion_H.png"));
                } catch(Exception e){
                    explosionImg = new Image("/shellExplosions/Explosion_H.png");
                }
            } else  projectile.endProjectile();
        }
        return explosionImg;
    }

}

