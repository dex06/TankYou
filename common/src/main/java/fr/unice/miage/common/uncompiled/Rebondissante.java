package fr.unice.miage.common.uncompiled;

import fr.unice.miage.common.game_objects.Projectile;
import fr.unice.miage.common.plugins.PlugInWeapon;

import java.util.List;

public class Rebondissante implements PlugInWeapon {



    public Rebondissante(){}


    public void moveProjectile(Projectile projectile){
        projectile.getPosition().setX(projectile.getPosition().getX() + projectile.getVelocity()*Math.cos(projectile.getRotation()));
        projectile.getPosition().setY(projectile.getPosition().getY() + projectile.getVelocity()*Math.sin(projectile.getRotation()));
    }

    public void checkProjectileOut(List<Projectile> projectiles){
        for (int counter = 0; counter < projectiles.size(); counter++) {
//            if(projectiles.get(counter).getPosition(.getX() > 600 || projectiles.get(counter).getPosition(.getX() < 0 || projectiles.get(counter).getPosition(.getY() > 600 || projectiles.get(counter).getPosition(.getY() < 0){
//                projectiles.remove(counter);
//            }
// POUR DES PROJECTILES QUI PEUVENT REBONDIR SUR DES MURS
            if(projectiles.get(counter).getPosition().getX() > 600 || projectiles.get(counter).getPosition().getX() < 0){
                projectiles.get(counter).setRotation(Math.PI - projectiles.get(counter).getRotation());
            }
            else if(projectiles.get(counter).getPosition().getY() > 600 || projectiles.get(counter).getPosition().getY() < 0){
                projectiles.get(counter).setRotation(-projectiles.get(counter).getRotation());
            }
        }
    }




    public void createWeapon(){ }
}
