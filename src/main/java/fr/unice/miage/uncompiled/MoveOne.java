package fr.unice.miage.uncompiled;

import fr.unice.miage.game_objects.Player;
import fr.unice.miage.plugins.PlugInMovement;

public class MoveOne implements PlugInMovement {
    public MoveOne(){}
    public void move(Player player){
        this.playerMove(player);
    }

    public void playerMove(Player player){
        player.addPosition(player.getVelocity());
    }


}
