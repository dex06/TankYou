package fr.unice.miage.utils;

import fr.unice.miage.game_objects.Player;
import fr.unice.miage.geom.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Finder {
    protected static List<Player> players = new ArrayList<>();

    public static Player findClosestPlayer(Player player){
        Player closestPlayer = player;
        double closestDistance = Double.POSITIVE_INFINITY;
        for(Player p : players){
            if(p != player){
                Vector2 subVector = p.getPosition().sub2(player.getPosition());
                double distanceBetweenPlayers = subVector.length();
                if(distanceBetweenPlayers <= closestDistance) {
                    closestDistance = distanceBetweenPlayers;
                    closestPlayer = p;
                }
            }
        }
        return closestPlayer;
    }

    public static void setPlayers(List<Player> pls){ players = pls;}
}
