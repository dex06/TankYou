package fr.unice.miage.uncompiled;

import fr.unice.miage.game_objects.Projectile;
import fr.unice.miage.plugins.PlugInWeapon;

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




    public void createWeapon(){ }
}
