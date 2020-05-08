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

public class Rebondissante implements PlugInWeapon {


    public void moveProjectile(Projectile projectile){
       if(!projectile.hasEnded()) projectile.addPosition(projectile.getVelocity());
    }

    public void draw(CanvasGUI canvas, Projectile projectile) {
        if(!projectile.hasEnded()) {
            double x = projectile.getX();
            double y = projectile.getY();
            GraphicsContext gc = canvas.getGraphicsContext();
            gc.setFill(Color.BLUE);
            gc.fillRect(x, y, 5, 5);
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
        if(Timer.getChrono() - player.lastShot > 1){

            Vector2 direction = new Vector2();
            if(Mouse.isMouseOn()){
                direction = Mouse.getLastShootingPosition().sub2(player.getPosition()).norm2();
            } else {
                Player p = Finder.findClosestPlayer(player);
                direction = p.getPosition().sub2(player.getPosition()).norm2();  //Vecteur normalisé
            }
            double damage = 10;
            double lifeSpan = 10;
            double xCenter = player.getX() + player.getSprite().getWidth()/2;
            double yCenter = player.getY() + player.getSprite().getHeight()/2;
            Vector2 position = new Vector2(xCenter, yCenter);
            Vector2 velocity = new Vector2(5 * direction.getX(), 5 * direction.getY());
            Sprite sprite = createSprite(player);
            player.addProjectile(new Projectile(this, player, position, velocity, sprite, "rebondissante"));


            player.lastShot = Timer.getChrono();
            System.out.println(player.getName() + " près de " + Finder.findClosestPlayer(player).getName());

        }
    }

    public Sprite createSprite(Player player){
        return new RectangleSprite(player,5,5, Color.FLORALWHITE, false);
    }

    public void applyPlayerImpact(Player player){
        player.setHealth(player.getHealth()-2);
    }

    public void applyObstacleCollision(Projectile projectile){
        projectile.reverseSpeed();
    }

    public void createWeapon(){ }
}
