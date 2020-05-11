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
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rebondissante implements PlugInWeapon {


    public void moveProjectile(Projectile projectile){
        if(Timer.getChrono() - projectile.getShotTime() >= 5) projectile.endProjectile();
       if(!projectile.hasEnded()) projectile.addPosition(projectile.getVelocity());
    }

    public void draw(CanvasGUI canvas, Projectile projectile) {
        if(!projectile.hasEnded()) {
            double x = projectile.getX();
            double y = projectile.getY();
            GraphicsContext gc = canvas.getGraphicsContext();
            gc.setFill(Color.BLUE);
            gc.fillOval(x, y, 5, 5);
        }
    }

    public void onProjectileOut(String axis, Projectile projectile){
        if(axis.equals("onX")) projectile.setSpeedX(-projectile.getSpeedX());
        else projectile.setSpeedY(-projectile.getSpeedY());
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
            player.addProjectile(new Projectile(this, player, position, velocity, sprite, Timer.getChrono(), "rebondissante"));
            player.setLastShot(Timer.getChrono());
            player.incrementNumberOfShots();
        }
    }

    public Sprite createSprite(Player player){
        return new RectangleSprite(player,5,5, Color.FLORALWHITE, false);
    }

    public void applyPlayerImpact(Player player){
        player.setHealth(player.getHealth()-10);
    }

    public void applyObstacleCollision(Projectile projectile, String inversion){

        if(inversion.equals("inverseX")) projectile.setSpeedX(-projectile.getSpeedX());
        else  projectile.setSpeedY(-projectile.getSpeedY());
    }
}
