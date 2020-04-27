package fr.unice.miage.uncompiled;

import fr.unice.miage.game_objects.Player;
import fr.unice.miage.geom.Vector2;
import fr.unice.miage.plugins.PlugInMovement;
import fr.unice.miage.utils.Finder;
import fr.unice.miage.utils.Randomizer;

public class MoveOne implements PlugInMovement {

    public void init(Player player){
        player.setPosition(new Vector2(100,100));
        player.setVelocity(new Vector2(0.1,0.1));
        player.setAcceleration(new Vector2());
        player.setRotation(0);
    }

    public void move(Player player){
        playerMove(player);
    }

    public void playerMove(Player player){
        randomMove(player);
        seekMove(player);
        fleeMove(player);
    }

    public void randomMove(Player player){
        //Random movements with varying acceleration between specified limits
        player.addVelocity(Randomizer.getRandomVector(-0.2,0.2));
        // Limiting the velocity
        player.getVelocity().limit(2);
        player.addPosition(player.getVelocity());
    }

    public void seekMove(Player player){
        Vector2 targetPosition = Finder.findClosestPlayer(player).getPosition();
        Vector2 desired_velocity = targetPosition.sub2(player.getPosition()).norm2().mult2(player.getMaxVelocity());
        Vector2 steering = desired_velocity.sub2(player.getVelocity());
        steering = steering.limit2(player.getMaxForce()).mult2(1/player.getMass());
        Vector2 velocity = player.getVelocity().add2(steering).limit2(player.getMaxSpeed());
        player.setPosition(player.getPosition().add2(velocity));
    }

    public void fleeMove(Player player){
        Vector2 targetPosition = Finder.findClosestPlayer(player).getPosition();
        Vector2 desired_velocity = targetPosition.sub2(player.getPosition()).norm2().mult2(player.getMaxVelocity());
        Vector2 steering = desired_velocity.sub2(player.getVelocity()).reverse2();
        steering = steering.limit2(player.getMaxForce()).mult2(1/player.getMass());
        Vector2 velocity = player.getVelocity().add2(steering).limit2(player.getMaxSpeed());
        player.setPosition(player.getPosition().add2(velocity));
    }
}
