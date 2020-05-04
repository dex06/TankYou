package fr.unice.miage.plugins.uncompiled;

import fr.unice.miage.common.Config;
import fr.unice.miage.common.game_objects.Player;
import fr.unice.miage.common.game_objects.Projectile;
import fr.unice.miage.common.plugins.PlugInCollision;
import fr.unice.miage.common.sprite.Sprite;

import java.util.Iterator;
import java.util.List;

public class CollisionOne implements PlugInCollision {
    double collisionDamage = 5;

    public void playerToPlayerCollisionDamage(Player player1, Player player2){
        double speedFactor1 = (Math.abs(player1.getSpeedX()) + Math.abs(player1.getSpeedY()))/2;
        double speedFactor2 = (Math.abs(player2.getSpeedX()) + Math.abs(player2.getSpeedY()))/2;
        player1.setHealth(player1.getHealth()-collisionDamage*speedFactor2);
        player2.setHealth(player2.getHealth()-collisionDamage*speedFactor1);
    }

    public void checkAllCollisions(List<Player> players){
        for(int i = 0; i < players.size()-1; i++){
            for(int j = i+1; j < players.size(); j++){
                checkPlayersCollision(players.get(i), players.get(j));
            }
        }
        for(Player player : players) checkPlayerToBorderCollision(player);
    }

    private void checkPlayersCollision(Player player1, Player player2){
        checkPlayerToPlayerCollision(player1, player2);
        //checkPlayerToWeaponCollision(player1, player2);
        //checkPlayerToWeaponCollision(player2, player1);
        //checkWeaponToWeaponCollision(player1, player2);
    }

    private void checkPlayerToBorderCollision(Player player){
        double x = player.getPosition().getX();
        double y = player.getPosition().getY();
        double w = player.getSprite().getWidth();
        double h = player.getSprite().getHeight();
        if ((x+w) > Config.getWorldWidth() || x < 0) {
            player.setSpeedX(-player.getSpeedX());
        }
        if ((y+h)> Config.getWorldHeight() || y < 0) {
            player.setSpeedY(-player.getSpeedY());
        }
    }


    private void checkPlayerToWeaponCollision(Player playerA, Player playerB) {
        Sprite playerSpriteA = playerA.getSprite();
        Iterator<Projectile> it = playerB.getProjectiles().iterator();
        while (it.hasNext()) {
            Projectile projectile = it.next();
            Sprite projectileSprite = projectile.getSprite();
            if (playerSpriteA.getBoundingShape().getBoundsInParent().intersects(projectileSprite.getBoundingShape().getBoundsInParent())) {
                System.out.println(playerA.getName() + " in collision with projectile " + projectile.getName());
                playerA.getHitByProjectile(projectile);
            }
        }
    }

    private void  checkPlayerToPlayerCollision(Player player1, Player player2) {
        Sprite playerSprite1 = player1.getSprite();
        Sprite playerSprite2 = player2.getSprite();
        if (playerSprite1.getBoundingShape().getBoundsInParent().intersects(playerSprite2.getBoundingShape().getBoundsInParent())) {
            System.out.println(player1.getName() + " in collision with " + player2.getName());
            player1.reverseSpeed();
            player1.getSprite().setRandomColor();
            player2.reverseSpeed();
            player2.getSprite().setRandomColor();
            playerToPlayerCollisionDamage(player1, player2);
        }
    }

    private void checkWeaponToWeaponCollision(Player player1, Player player2) {
        List<Projectile> weaponProjectiles1 = player1.getProjectiles();
        List<Projectile> weaponProjectiles2 = player2.getProjectiles();
        for(int k = 0; k < weaponProjectiles1.size()-1; k++){
            for(int l = 0; l < weaponProjectiles2.size()-1; l++){
                Sprite projectileSprite1 = weaponProjectiles1.get(k).getSprite();
                Sprite projectileSprite2 = weaponProjectiles2.get(l).getSprite();
                if (projectileSprite1.getBoundingShape().getBoundsInParent().intersects(projectileSprite2.getBoundingShape().getBoundsInParent())){
                    weaponProjectiles1.get(k).collidedWith(weaponProjectiles2.get(l));
                    weaponProjectiles2.get(l).collidedWith(weaponProjectiles1.get(k));
                }
            }
        }
    }
}
