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

public class ObusL implements PlugInWeapon {


    public void moveProjectile(Projectile projectile){
        if(Timer.getChrono() - projectile.getShotTime() >= 5) projectile.setHit();
        if(!projectile.hasEnded()) projectile.addPosition(projectile.getVelocity());

    }

    public void draw(CanvasGUI canvas, Projectile projectile) {
        Image img = getSprite(projectile, Timer.getChrono());
        //canvas.getGraphicsContext().drawImage(img, projectile.getX()-30, projectile.getY()-30, 60, 60);
        double rot = projectile.getRotation();

        double x = projectile.getX();
        double y = projectile.getY();
        double w = 100;
        double h = 75;
        ImageView iv = new ImageView(img);
        iv.setFitWidth(w);
        iv.setFitHeight(h);
        iv.setRotate(rot);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        Image rotatedImage = iv.snapshot(params, null);
        GraphicsContext c= canvas.getGraphicsContext();
        c.save();
        c.drawImage(rotatedImage, x, y);
        c.restore();
    }

    public void onProjectileOut(String axis, Projectile projectile){
        projectile.endProjectile();

    }

    public void shoot(Player player) {
        if(Timer.getChrono() - player.getLastShot() > 1){

            double xCenter = player.getX() + player.getSprite().getWidth()/2;
            double yCenter = player.getY() + player.getSprite().getHeight()/2;
            Vector2 position = new Vector2(xCenter, yCenter);

            Vector2 direction;
            if(Mouse.isMouseOn()){
                direction = Mouse.getLastShootingPosition().sub2(player.getPosition()).norm2();
            } else {
                Player p = Finder.findClosestPlayer(player);
                double xCenterOp = p.getX() + p.getSprite().getWidth()/2;
                double yCenterOp = p.getY() + p.getSprite().getHeight()/2;
                Vector2 opposition = new Vector2(xCenterOp, yCenterOp);
                direction = opposition.sub2(position).norm2();  //Vecteur normalisé
            }

            Vector2 velocity = new Vector2(5 * direction.getX(), 5 * direction.getY());

            Sprite sprite = createSprite(player);
            Projectile projectile = new Projectile(this, player, position, velocity, sprite, Timer.getChrono(), "obus léger");
            projectile.setRotation(Rotation.rotation2Vectors(velocity, position));
            player.addProjectile(projectile);
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
        Image explosionImg= new Image(ObusL.class.getClassLoader().getResourceAsStream("/shells/Light_Shell.png"));
        if(!projectile.hasEnded() & !projectile.hasHit()) {
            explosionImg= new Image(ObusL.class.getClassLoader().getResourceAsStream("/shells/Light_Shell.png"));

        }
        if(!projectile.hasEnded() & projectile.hasHit()) {
            double hitTime = projectile.getHitTime();
            double difTime = currentTime - hitTime;
            if (difTime <= 0.4) {
                explosionImg = new Image(ObusL.class.getClassLoader().getResourceAsStream("/shellExplosions/Explosion_A.png"));
            }
            if (difTime > 0.4 & difTime <= 0.8) {
                explosionImg = new Image(ObusL.class.getClassLoader().getResourceAsStream("/shellExplosions/Explosion_B.png"));
            }
            if (difTime > 0.8 & difTime <= 1.2) {
                explosionImg = new Image(ObusL.class.getClassLoader().getResourceAsStream("/shellExplosions/Explosion_C.png"));
            }
            if (difTime > 1.2 & difTime <= 1.6) {
                explosionImg = new Image(ObusL.class.getClassLoader().getResourceAsStream("/shellExplosions/Explosion_D.png"));
            }
            if (difTime > 2 & difTime <= 2.4) {
                explosionImg = new Image(ObusL.class.getClassLoader().getResourceAsStream("/shellExplosions/Explosion_E.png"));
            }
            if (difTime > 2.4 & difTime <= 2.8) {
                explosionImg = new Image(ObusL.class.getClassLoader().getResourceAsStream("/shellExplosions/Explosion_F.png"));
            }
            if (difTime > 3.2 & difTime <= 3.6) {
                explosionImg = new Image(ObusL.class.getClassLoader().getResourceAsStream("/shellExplosions/Explosion_G.png"));
            }
            if (difTime > 4) {
                explosionImg = new Image(ObusL.class.getClassLoader().getResourceAsStream("/shellExplosions/Explosion_H.png"));
                projectile.endProjectile();
            }
        }
        return explosionImg;
    }

}

