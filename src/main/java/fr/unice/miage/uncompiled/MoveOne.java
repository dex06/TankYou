package fr.unice.miage.uncompiled;

import fr.unice.miage.game_objects.Player;
import fr.unice.miage.geom.Vector2;
import fr.unice.miage.plugins.PlugInMovement;
import fr.unice.miage.utils.Finder;
import fr.unice.miage.utils.Randomizer;

public class MoveOne implements PlugInMovement {
    protected double maxSpeed = 5;
    protected double maxVelocity = 5;
    protected double maxForce = 5;
    protected double mass = 3;

    public MoveOne(){}
    public void move(Player player){
        this.playerMove(player);
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
        Vector2 desired_velocity = targetPosition.sub2(player.getPosition()).norm2().mult2(maxVelocity);
        Vector2 steering = desired_velocity.sub2(player.getVelocity());
        steering = steering.limit2(maxForce).mult2(1/mass);
        Vector2 velocity = player.getVelocity().add2(steering).limit2(maxSpeed);
        player.setPosition(player.getPosition().add2(velocity));
    }

    public void fleeMove(Player player){
        Vector2 targetPosition = Finder.findClosestPlayer(player).getPosition();
        Vector2 desired_velocity = targetPosition.sub2(player.getPosition()).norm2().mult2(maxVelocity);
        Vector2 steering = desired_velocity.sub2(player.getVelocity()).reverse2();
        steering = steering.limit2(maxForce).mult2(1/mass);
        Vector2 velocity = player.getVelocity().add2(steering).limit2(maxSpeed);
        player.setPosition(player.getPosition().add2(velocity));
    }
}
