package fr.unice.miage.uncompiled;

import fr.unice.miage.game_objects.Player;
import fr.unice.miage.plugins.PlugInMovement;
import fr.unice.miage.utils.Randomizer;

public class MoveOne implements PlugInMovement {
    public MoveOne(){}
    public void move(Player player){
        this.playerMove(player);
    }

    public void playerMove(Player player){
        //Random movements with varying acceleration between specified limits
        player.addVelocity(Randomizer.getRandomVector(-0.2,0.2));
        // Limiting the velocity
        player.getVelocity().limit(2);
        player.addPosition(player.getVelocity());
    }


}
