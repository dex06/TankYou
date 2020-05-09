package fr.unice.miage.plugins.uncompiled;

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

public class Mine implements PlugInWeapon {


    public void moveProjectile(Projectile projectile){
        if(Timer.getChrono() - projectile.getShotTime() >= 10) projectile.endProjectile();
        //if(!projectile.hasEnded()) projectile.addPosition(projectile.getVelocity());
    }

    public void draw(CanvasGUI canvas, Projectile projectile) {
        if(!projectile.hasEnded()) {
            double x = projectile.getX();
            double y = projectile.getY();
            GraphicsContext gc = canvas.getGraphicsContext();
            gc.setFill(Color.NAVY);
            gc.strokeOval(x, y, 15, 15);
            gc.fillOval(x, y, 15, 15);
            if(Math.round(Timer.getChrono()) % 2 == 0){
                gc.setFill(Color.RED);
                gc.strokeOval(x + 4.5, y + 4.5, 6, 6);
                gc.fillOval(x + 4.5, y + 4.5, 6, 6);
            }
        }
    }

    public void onProjectileOut(String axis, Projectile projectile){
        if(axis.equals("onX")){
            projectile.setSpeedX(-projectile.getSpeedX());
        }
        if(axis.equals("onY")){
            projectile.setSpeedY(-projectile.getSpeedY());
        }

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
            player.incrementNumberOfShots();

            player.setLastShot(Timer.getChrono());
            System.out.println(player.getName() + " près de " + Finder.findClosestPlayer(player).getName());

        }
    }

    public Sprite createSprite(Player player){
        return new RectangleSprite(player,5,5, Color.FLORALWHITE, false);
    }

    public void applyPlayerImpact(Player player){
        player.setHealth(player.getHealth()-30);
    }

    public void applyObstacleCollision(Projectile projectile){
        projectile.reverseSpeed();
    }
}
