package fr.unice.miage.plugins.uncompiled;

import fr.unice.miage.common.Config;
import fr.unice.miage.common.game_objects.Obstacle;
import fr.unice.miage.common.game_objects.Player;
import fr.unice.miage.common.game_objects.Projectile;
import fr.unice.miage.common.plugins.PlugInCollision;
import fr.unice.miage.common.sprite.Sprite;

import java.util.Iterator;
import java.util.List;

public class CollisionOne implements PlugInCollision {

    public void checkAllCollisions(List<Player> players, List<Obstacle> obstacles){
        for(int i = 0; i < players.size()-1; i++){
            for(int j = i+1; j < players.size(); j++){
                checkPlayersCollision(players.get(i), players.get(j));
            }
        }
        for(Player player : players) {
            checkPlayerToBorderCollision(player);
            checkPlayerToObstacleCollision(player, obstacles);
            checkWeaponToBorderCollision(player);
            checkWeaponToObstacleCollision(player, obstacles);
        }
    }

    private void checkPlayersCollision(Player player1, Player player2){
        checkPlayerToPlayerCollision(player1, player2);
        checkPlayerToWeaponCollision(player1, player2);
        checkPlayerToWeaponCollision(player2, player1);
        //checkWeaponToWeaponCollision(player1, player2);
    }

    private void checkPlayerToBorderCollision(Player player){
        if(player.hasGraphic()) {
            double x = player.getPosition().getX();
            double y = player.getPosition().getY();
            double w = player.getSprite().getWidth();
            double h = player.getSprite().getHeight();
            if ((x + w) > Config.getWorldWidth() || x < 0) {
                player.setSpeedX(-player.getSpeedX()*2);
            }
            if ((y + h) > Config.getWorldHeight() || y < 0) {
                player.setSpeedY(-player.getSpeedY()*2);
            }
        }
    }

    public void checkPlayerToObstacleCollision(Player player, List<Obstacle> obstacles){
        for(Obstacle obstacle : obstacles){
            if(checkPlayerHitsObstacle(obstacle, player)){
                obstacle.setPlayerCollision(player);
            }
        }
    }

    public void checkWeaponToObstacleCollision(Player player, List<Obstacle> obstacles){
        for(Obstacle obs : obstacles){
            for(Projectile prj : player.getProjectiles()){
                if(checkProjectileHitsObstacle(prj, obs)){
                    prj.applyObstacleCollision(obs);
                }
            }
        }
    }

    public void checkWeaponToBorderCollision(Player player){
        if(player.hasGraphic()) {
            for (Projectile projectile : player.getProjectiles()) {
                double w = projectile.getSprite().getWidth();
                double h = projectile.getSprite().getHeight();
                double x = projectile.getPosition().getX();
                double y = projectile.getPosition().getY();
                if ((x + w) > Config.getWorldWidth() || x < 0) {
                    player.onProjectileOut("onX", projectile);
                }
                if ((y + h) > Config.getWorldHeight() || y < 0) {
                    player.onProjectileOut("onY", projectile);
                }

            }
        }
    }


    private void checkPlayerToWeaponCollision(Player playerA, Player playerB) {
        if(playerA.hasGraphic()) {
            Iterator<Projectile> it = playerB.getProjectiles().iterator();
            while (it.hasNext()) {
                Projectile projectile = it.next();
                if (checkProjectileHitsPlayer(projectile, playerA)) {
                    playerA.getHitByProjectile(projectile);
                    projectile.endProjectile();
                }
            }
        }
    }

    private void  checkPlayerToPlayerCollision(Player player1, Player player2) {
        if(player1.hasGraphic() && player2.hasGraphic()) {
            Sprite playerSprite1 = player1.getSprite();
            Sprite playerSprite2 = player2.getSprite();
            if (playerSprite1.getBoundingShape().getBoundsInParent().intersects(playerSprite2.getBoundingShape().getBoundsInParent())) {
                if(player1.isAlive()) {
                    player1.reverseSpeed();
                    player1.getVelocity().mult2(2);
                    player1.getSprite().setRandomColor();
                }
                if (player2.isAlive()){
                    player2.reverseSpeed();
                    player2.getVelocity().mult2(2);
                    player2.getSprite().setRandomColor();
                }
                if(player1.isAlive() & player2.isAlive()) playerToPlayerCollisionDamage(player1, player2);
            }
        }
    }

    public void playerToPlayerCollisionDamage(Player player1, Player player2){
        double speedFactor1 = (Math.abs(player1.getSpeedX()) + Math.abs(player1.getSpeedY()))/2;
        double speedFactor2 = (Math.abs(player2.getSpeedX()) + Math.abs(player2.getSpeedY()))/2;
        if(player1.isAlive() & player2.isAlive()) {
            player1.setHealth(player1.getHealth() - 5 * speedFactor2);
            player2.setHealth(player2.getHealth() - 5 * speedFactor1);
        }
    }

    private void checkWeaponToWeaponCollision(Player player1, Player player2) {
        if(player1.hasWeapon() && player2.hasWeapon()) {
            List<Projectile> weaponProjectiles1 = player1.getProjectiles();
            List<Projectile> weaponProjectiles2 = player2.getProjectiles();
            for (int k = 0; k < weaponProjectiles1.size() - 1; k++) {
                for (int l = 0; l < weaponProjectiles2.size() - 1; l++) {
                    Sprite projectileSprite1 = weaponProjectiles1.get(k).getSprite();
                    Sprite projectileSprite2 = weaponProjectiles2.get(l).getSprite();
                    if (projectileSprite1.getBoundingShape().getBoundsInParent().intersects(projectileSprite2.getBoundingShape().getBoundsInParent())) {
                        weaponProjectiles1.get(k).collidedWith(weaponProjectiles2.get(l));
                        weaponProjectiles2.get(l).collidedWith(weaponProjectiles1.get(k));
                    }
                }
            }
        }
    }

    private boolean checkProjectileHitsPlayer(Projectile prj,Player player){

        double pjxMin = prj.getX();
        double pjxMax = pjxMin + prj.getSprite().getWidth();
        double pjyMin = prj.getY();
        double pjyMax = pjyMin + prj.getSprite().getHeight();
        double plxMin = player.getX();
        double plxMax = plxMin + player.getSprite().getWidth();
        double plyMin = player.getY();
        double plyMax = plyMin + player.getSprite().getHeight();

        if(pjxMax >= plxMin & pjyMax >= plyMin & pjxMax <= plxMax & pjyMax <= plyMax) return true;
        if(pjxMin >= plxMin & pjyMin >= plyMin & pjxMin <= plxMax & pjyMin <= plyMax) return true;
        if(pjxMax >= plxMin & pjyMin >= plyMin & pjxMax <= plxMax & pjyMin <= plyMax) return true;
        return pjxMin >= plxMin & pjyMax >= plyMin & pjxMin <= plxMax & pjyMax <= plyMax;
    }

    private boolean checkPlayerHitsObstacle(Obstacle obs,Player player){

        double pjxMin = player.getX();
        double pjxMax = pjxMin + player.getSprite().getWidth();
        double pjyMin = player.getY();
        double pjyMax = pjyMin + player.getSprite().getHeight();
        double plxMin = obs.getPosition().getX();
        double plxMax = plxMin + obs.getSprite().getWidth();
        double plyMin = obs.getPosition().getY();
        double plyMax = plyMin + obs.getSprite().getHeight();

        if(pjxMax >= plxMin & pjyMax >= plyMin & pjxMax <= plxMax & pjyMax <= plyMax) return true;
        if(pjxMin >= plxMin & pjyMin >= plyMin & pjxMin <= plxMax & pjyMin <= plyMax) return true;
        if(pjxMax >= plxMin & pjyMin >= plyMin & pjxMax <= plxMax & pjyMin <= plyMax) return true;
        return pjxMin >= plxMin & pjyMax >= plyMin & pjxMin <= plxMax & pjyMax <= plyMax;
    }

    private boolean checkProjectileHitsObstacle(Projectile prj, Obstacle obs){

        double pjxMin = prj.getX();
        double pjxMax = pjxMin + prj.getSprite().getWidth();
        double pjyMin = prj.getY();
        double pjyMax = pjyMin + prj.getSprite().getHeight();
        double plxMin = obs.getPosition().getX();
        double plxMax = plxMin + obs.getSprite().getWidth();
        double plyMin = obs.getPosition().getY();
        double plyMax = plyMin + obs.getSprite().getHeight();

        if(pjxMax >= plxMin & pjyMax >= plyMin & pjxMax <= plxMax & pjyMax <= plyMax) return true;
        if(pjxMin >= plxMin & pjyMin >= plyMin & pjxMin <= plxMax & pjyMin <= plyMax) return true;
        if(pjxMax >= plxMin & pjyMin >= plyMin & pjxMax <= plxMax & pjyMin <= plyMax) return true;
        return pjxMin >= plxMin & pjyMax >= plyMin & pjxMin <= plxMax & pjyMax <= plyMax;
    }
}
