package fr.unice.miage.uncompiled;

import fr.unice.miage.game_objects.Player;
import fr.unice.miage.geom.Vector2;
import fr.unice.miage.plugins.PlugInMovement;
import fr.unice.miage.utils.Finder;
import fr.unice.miage.utils.Randomizer;

public class MoveOne implements PlugInMovement {
    protected Vector2 position;
    protected Vector2 velocity;
    protected Vector2 acceleration;
    protected double rotation;
    protected double maxSpeed = 5;
    protected double maxVelocity = 5;
    protected double maxForce = 5;
    protected double mass = 3;

    public MoveOne(){
        this.position = new Vector2(100,100);
        this.velocity = new Vector2(0.1,0.1);
        this.acceleration = new Vector2();
        this.rotation = 0;
    }

    // Methods for speedX and speedY
    public void setSpeedX(double x){ velocity = new Vector2(x, velocity.getY()); }
    public void setSpeedY(double y){ velocity = new Vector2(velocity.getX(), y); }
    public double getSpeedX(){ return velocity.getX(); }
    public double getSpeedY(){ return velocity.getY(); }

    /* Vectors */
    // Methods for position vectors
    public Vector2 getPosition(){ return position; }
    public void setPosition(Vector2 v){ position = v;}
    public void addPosition(Vector2 v){ position.add(v); }

    // Methods for velocity vectors
    public Vector2 getVelocity() { return velocity; }
    public void setVelocity(Vector2 v){ velocity = v; }
    public void addVelocity(Vector2 v){ velocity.add(v); }

    // Methods for acceleration vectors
    public Vector2 getAcceleration() { return acceleration; }
    public void setAcceleration(Vector2 v){ acceleration = v; }
    public void addAcceleration(Vector2 v){ acceleration.add(v); }


    public void move(Player player){
        playerMove(player);
    }

    public void playerMove(Player player){
        randomMove();
        seekMove(player);
        fleeMove(player);
    }

    public void randomMove(){
        //Random movements with varying acceleration between specified limits
        addVelocity(Randomizer.getRandomVector(-0.2,0.2));
        // Limiting the velocity
        getVelocity().limit(2);
        addPosition(getVelocity());
    }

    public void seekMove(Player player){
        Vector2 targetPosition = Finder.findClosestPlayer(player).getPosition();
        Vector2 desired_velocity = targetPosition.sub2(getPosition()).norm2().mult2(maxVelocity);
        Vector2 steering = desired_velocity.sub2(getVelocity());
        steering = steering.limit2(maxForce).mult2(1/mass);
        Vector2 velocity = getVelocity().add2(steering).limit2(maxSpeed);
        setPosition(getPosition().add2(velocity));
    }

    public void fleeMove(Player player){
        Vector2 targetPosition = Finder.findClosestPlayer(player).getPosition();
        Vector2 desired_velocity = targetPosition.sub2(getPosition()).norm2().mult2(maxVelocity);
        Vector2 steering = desired_velocity.sub2(getVelocity()).reverse2();
        steering = steering.limit2(maxForce).mult2(1/mass);
        Vector2 velocity = getVelocity().add2(steering).limit2(maxSpeed);
        setPosition(getPosition().add2(velocity));
    }
}
