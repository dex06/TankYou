package fr.unice.miage.plugins.uncompiled;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.game_objects.Player;
import fr.unice.miage.common.game_objects.Projectile;
import fr.unice.miage.common.geom.Vector2;
import fr.unice.miage.common.plugins.PlugInWeapon;
import fr.unice.miage.common.utils.Finder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class Standard implements PlugInWeapon {



    public Standard(){}


    public void moveProjectile(Projectile projectile){
        projectile.getPosition().setX(projectile.getPosition().getX() + projectile.getVelocity()*Math.cos(projectile.getRotation()));
        projectile.getPosition().setY(projectile.getPosition().getY() + projectile.getVelocity()*Math.sin(projectile.getRotation()));
    }

    public void checkProjectileOut(List<Projectile> projectiles){
        for (int counter = 0; counter < projectiles.size(); counter++) {
            if(projectiles.get(counter).getPosition().getX() > 600 || projectiles.get(counter).getPosition().getX() < 0 || projectiles.get(counter).getPosition().getY() > 600 || projectiles.get(counter).getPosition().getY() < 0){
                projectiles.remove(counter);
            }
// POUR DES PROJECTILES QUI PEUVENT REBONDIR SUR DES MURS
//            if(projectiles.get(counter).getPosition(.getX() > 600 || projectiles.get(counter).getPosition(.getX() < 0){
//                projectiles.get(counter).rotation = Math.PI - projectiles.get(counter).rotation;
//            }
//            else if(projectiles.get(counter).getPosition(.getY() > 600 || projectiles.get(counter).getPosition(.getY() < 0){
//                projectiles.get(counter).rotation = -projectiles.get(counter).rotation;
//            }
        }
    }


    @Override
    public void shot(Player player, long currentTime) {
        if((currentTime / 1000000000) - player.lastShot > 1){
            Player p = Finder.findClosestPlayer(player);
            double direction = Math.atan2(p.getPosition().getY() - player.getPosition().getY(), p.getPosition().getX() - player.getPosition().getX());
//        System.out.println(this.getName() + " " + direction);

            double xCenter = player.getPosition().getX() + player.getSprite().getWidth()/2;
            double yCenter =player.getPosition().getY() + player.getSprite().getHeight()/2;
            double longueur = Math.sqrt(Math.pow(player.getSprite().getWidth()/2, 2) + Math.pow(player.getSprite().getHeight()/2, 2));
//        new Vector2(pm.getPosition().getX(), pm.getPosition().getY());
            player.getProjectiles().add(new Projectile(new Vector2(xCenter + longueur*Math.cos(direction),
                    yCenter + longueur*Math.sin(direction)),
                    direction));
            System.out.println(player.getName() + " shot ");
            player.lastShot = currentTime / 1000000000;
            System.out.println(player.getName() + " près de " + Finder.findClosestPlayer(player).getName());
//        projectiles.add(new Projectile(new Vector2(pm.getPosition().getX(), pm.getPosition().getY()), direction));
        }
    }

    @Override
    public void draw(CanvasGUI canvas, List<Projectile> projectile) {
        for (int counter = 0; counter < projectile.size(); counter++) {
            double x = projectile.get(counter).getPosition().getX();
            double y = projectile.get(counter).getPosition().getY();
            GraphicsContext gc = canvas.getGraphicsContext();
            gc.setFill(Color.BLACK);
            gc.fillRect(x, y, projectile.get(counter).getSizeRect().getX(), projectile.get(counter).getSizeRect().getY());
        }
    }





    public void createWeapon(){ }
}
