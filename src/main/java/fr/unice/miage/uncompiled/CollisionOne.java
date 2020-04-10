package fr.unice.miage.uncompiled;

import fr.unice.miage.game_objects.Player;
import fr.unice.miage.game_objects.Projectile;
import fr.unice.miage.plugins.PlugInCollision;
import fr.unice.miage.plugins.PlugInWeapon;
import fr.unice.miage.sprite.Sprite;

import java.util.Iterator;
import java.util.List;

public class CollisionOne implements PlugInCollision {

    public void checkAllCollisions(List<Player> players){
        for(int i = 0; i < players.size()-2; i++){
            for(int j = i+1; j < players.size()-1; j++){
                this.checkPlayersCollision(players.get(i), players.get(j));
            }
        }
    }

    private void checkPlayersCollision(Player player1, Player player2){
        this.checkPlayerToPlayerCollision(player1, player2);
        this.checkPlayerToWeaponCollision(player1, player2);
        this.checkPlayerToWeaponCollision(player2, player1);
        this.checkWeaponToWeaponCollision(player1, player2);
    }


    private void checkPlayerToWeaponCollision(Player playerA, Player playerB) {
        Sprite playerSpriteA = playerA.getPlayerSprite();
        List<PlugInWeapon> playerWeaponsB = playerB.getPlayerWeapons();
        for(PlugInWeapon weapon : playerWeaponsB) {
            Iterator<Projectile> it = weapon.getWeaponProjectiles().iterator();
            while (it.hasNext()) {
                Projectile projectile = it.next();
                Sprite projectileSprite = projectile.getSprite();
                if (playerSpriteA.getBoundingShape().getBoundsInParent().intersects(projectileSprite.getBoundingShape().getBoundsInParent())) {
                    System.out.println(playerA.getName() + " in collision with projectile " + projectile.getName());
                    playerA.getHitByProjectile(projectile);
                }
            }
        }
    }

    private void  checkPlayerToPlayerCollision(Player player1, Player player2) {
        Sprite playerSprite1 = player1.getPlayerSprite();
        Sprite playerSprite2 = player2.getPlayerSprite();
        if (playerSprite1.getBoundingShape().getBoundsInParent().intersects(playerSprite2.getBoundingShape().getBoundsInParent())) {
            System.out.println(player1.getName() + " in collision with " + player2.getName());
            player1.getHitByPlayer(player2);
            player2.getHitByPlayer(player1);
        }
    }

    private void checkWeaponToWeaponCollision(Player player1, Player player2) {
        List<PlugInWeapon> playerWeapons1 = player1.getPlayerWeapons();
        List<PlugInWeapon> playerWeapons2 = player2.getPlayerWeapons();
        for(int i = 0; i < playerWeapons1.size()-1; i++){
            for(int j = 0; j < playerWeapons2.size()-1; j++){
                List<Projectile> weaponProjectiles1 = playerWeapons1.get(i).getWeaponProjectiles();
                List<Projectile> weaponProjectiles2 = playerWeapons2.get(j).getWeaponProjectiles();
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
    }
}
