package fr.unice.miage.plugins.uncompiled.collision_plugins;

import fr.unice.miage.common.Config;
import fr.unice.miage.common.game_objects.Obstacle;
import fr.unice.miage.common.game_objects.Player;
import fr.unice.miage.common.game_objects.Projectile;
import fr.unice.miage.common.geom.Vector2;
import fr.unice.miage.common.plugins.PlugInCollision;
import fr.unice.miage.common.sprite.Sprite;
import fr.unice.miage.common.utils.Randomizer;

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
        checkWeaponToWeaponCollision(player1, player2);
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
                if(!player.getBlocked()){
                    player.setBlocked(true);
                }
            }
        }
    }

    public void checkWeaponToObstacleCollision(Player player, List<Obstacle> obstacles){
        for(Obstacle obs : obstacles){
            for(Projectile prj : player.getProjectiles()){
                if(checkProjectileHitsObstacle(prj, obs)){
                    double pLeft = prj.getX();
                    double pRight = pLeft + prj.getSprite().getWidth();
                    double pTop = prj.getY();
                    double pBottom = pTop + prj.getSprite().getHeight();
                    double oLeft = obs.getPosition().getX();
                    double oRight = oLeft + obs.getSprite().getHeight();
                    double oTop = obs.getPosition().getY();
                    double oBottom = oTop + obs.getSprite().getHeight();
                    double leftX = Math.abs(pLeft - oLeft);
                    double rightX = Math.abs(pRight - oRight);
                    double topY = Math.abs(pTop - oTop);
                    double bottomY = Math.abs(pBottom - oBottom);
                    if((leftX < topY & leftX < bottomY) | (rightX < topY & rightX < bottomY)){
                        prj.applyObstacleCollision(obs, "inverseX");
                    }
                    else prj.applyObstacleCollision(obs, "inverseY");
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
                    //projectile.endProjectile();
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
                    if(player1.getBlocked()){
                        double x = player1.getPosition().getX();
                        double y = player1.getPosition().getY();
                        Vector2 newPosition = new Vector2(x + Randomizer.getRandomIntInRange(-10,10), y + Randomizer.getRandomIntInRange(-10,10));
                        player1.setPosition(newPosition);
                        player1.setBlocked(false);
                    } else {
                        player1.reverseSpeed();
                        player1.getVelocity().mult2(2);
                        player1.getSprite().setRandomColor();
                    }
                }
                if (player2.isAlive()){
                    if(player2.getBlocked()){
                        double x = player2.getPosition().getX();
                        double y = player2.getPosition().getY();
                        Vector2 newPosition = new Vector2(x + Randomizer.getRandomIntInRange(-10,10), y + Randomizer.getRandomIntInRange(-10,10));
                        player2.setPosition(newPosition);
                        player2.setBlocked(false);
                    } else {
                        player2.reverseSpeed();
                        player2.getVelocity().mult2(2);
                        player2.getSprite().setRandomColor();
                    }
                }
                if(player1.isAlive()) player1.setBlocked(true);
                if(player2.isAlive()) player2.setBlocked(true);
                if(player1.isAlive() & player2.isAlive()) {
                    playerToPlayerCollisionDamage(player1, player2);
                }
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
                    if (checkProjectileHitsProjectile(weaponProjectiles1.get(k), weaponProjectiles2.get(l))) {
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

    private boolean checkProjectileHitsProjectile(Projectile prj1, Projectile prj2){
        double pjxMin = prj1.getX();
        double pjxMax = pjxMin + prj1.getSprite().getWidth();
        double pjyMin = prj1.getY();
        double pjyMax = pjyMin + prj1.getSprite().getHeight();
        double plxMin = prj2.getX();
        double plxMax = plxMin + prj2.getSprite().getWidth();
        double plyMin = prj2.getY();
        double plyMax = plyMin + prj2.getSprite().getHeight();

        if(pjxMax >= plxMin & pjyMax >= plyMin & pjxMax <= plxMax & pjyMax <= plyMax) return true;
        if(pjxMin >= plxMin & pjyMin >= plyMin & pjxMin <= plxMax & pjyMin <= plyMax) return true;
        if(pjxMax >= plxMin & pjyMin >= plyMin & pjxMax <= plxMax & pjyMin <= plyMax) return true;
        return pjxMin >= plxMin & pjyMax >= plyMin & pjxMin <= plxMax & pjyMax <= plyMax;
    }
}
