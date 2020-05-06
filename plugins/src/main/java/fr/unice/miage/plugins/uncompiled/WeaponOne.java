package fr.unice.miage.plugins.uncompiled;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.game_objects.Player;
import fr.unice.miage.common.game_objects.Projectile;
import fr.unice.miage.common.geom.Vector2;
import fr.unice.miage.common.plugins.PlugInWeapon;
import fr.unice.miage.common.sprite.RectangleSprite;
import fr.unice.miage.common.sprite.Sprite;
import fr.unice.miage.common.utils.Finder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class WeaponOne implements PlugInWeapon {


    public void moveProjectile(Projectile projectile){
        projectile.addPosition(projectile.getVelocity());
    }

    public void onProjectileOut(String axis, Projectile projectile){
        if(axis.equals("onX")){
            projectile.setSpeedX(-projectile.getSpeedX());
        }
        if(axis.equals("onY")){
            projectile.setSpeedY(-projectile.getSpeedY());
        }

    }

    @Override
    public void shot(Player player, long currentTime) {
        if((currentTime / 1000000000) - player.lastShot > 1){
            Player p = Finder.findClosestPlayer(player);
            //double direction = Math.atan2(p.getPosition().getY() - player.getPosition().getY(), p.getPosition().getX() - player.getPosition().getX());
            Vector2 direction = p.getPosition().sub2(player.getPosition()).norm2();  //Vecteur normalisé
            double xCenter = player.getX() + player.getSprite().getWidth()/2;
            double yCenter = player.getY() + player.getSprite().getHeight()/2;
            Vector2 position = new Vector2(xCenter, yCenter);
            Vector2 velocity = new Vector2(5 * direction.getX(), 5 * direction.getY());
            double longueur = Math.sqrt(Math.pow(player.getSprite().getWidth()/2, 2) + Math.pow(player.getSprite().getHeight()/2, 2));
            Sprite sprite = createSprite(player);
            player.addProjectile(new Projectile(player, position, velocity, sprite, "rebondissante"));
//
            System.out.println(player.getName() + " shot ");
            player.lastShot = currentTime / 1000000000;
            System.out.println(player.getName() + " près de " + Finder.findClosestPlayer(player).getName());
//        projectiles.add(new Projectile(new Vector2(pm.getPosition().getX(), pm.getPosition().getY()), direction));
        }
    }

    public Sprite createSprite(Player player){
        return new RectangleSprite(player,5,5, Color.RED, false);
    }

    public void draw(CanvasGUI canvas, List<Projectile> projectile) {
        for (int counter = 0; counter < projectile.size(); counter++) {
            double x = projectile.get(counter).getPosition().getX();
            double y = projectile.get(counter).getPosition().getY();
            GraphicsContext gc = canvas.getGraphicsContext();
            gc.setFill(Color.BLACK);
            gc.fillRect(x, y, 5,5);
        }
    }



    public void createWeapon(){ }
}
