package fr.unice.miage.common.uncompiled;

import fr.unice.miage.common.game_objects.Player;
import fr.unice.miage.common.geom.Vector2;
import fr.unice.miage.common.plugins.PlugInMovement;
import fr.unice.miage.common.utils.Finder;
import fr.unice.miage.common.utils.Randomizer;

import java.util.Random;

public class Equilibre implements PlugInMovement {
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
        int[] cpmts = {1,1,1,1,2,2,2,2,3,3,3,3};
        int randCpmt = getRandom(cpmts);
        switch (randCpmt){
            case 1 :
                randomMove(player);
                break;
            case 2 :
                seekMove(player);
                break;
            case 3:
                fleeMove(player);
                break;
        }
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



    private static int getRandom(int[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }
}
