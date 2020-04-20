package fr.unice.miage.uncompiled;

import fr.unice.miage.game_objects.Projectile;
import fr.unice.miage.plugins.PlugInWeapon;

import java.util.ArrayList;
import java.util.List;

public class WeaponOne implements PlugInWeapon {



    public WeaponOne(){}


    public void moveProjectile(Projectile projectile){
        projectile.position.setX(projectile.position.getX() + projectile.velocity*Math.cos(projectile.rotation));
        projectile.position.setY(projectile.position.getY() + projectile.velocity*Math.sin(projectile.rotation));
    }

    public void checkProjectileOut(List<Projectile> projectiles){
        for (int counter = 0; counter < projectiles.size(); counter++) {
//            if(projectiles.get(counter).position.getX() > 600 || projectiles.get(counter).position.getX() < 0 || projectiles.get(counter).position.getY() > 600 || projectiles.get(counter).position.getY() < 0){
//                projectiles.remove(counter);
//            }
// POUR DES PROJECTILES QUI PEUVENT REBONDIR SUR DES MURS
            if(projectiles.get(counter).position.getX() > 600 || projectiles.get(counter).position.getX() < 0){
                projectiles.get(counter).rotation = Math.PI - projectiles.get(counter).rotation;
            }
            else if(projectiles.get(counter).position.getY() > 600 || projectiles.get(counter).position.getY() < 0){
                projectiles.get(counter).rotation = -projectiles.get(counter).rotation;
            }
        }
    }




    public void createWeapon(){ }
}
