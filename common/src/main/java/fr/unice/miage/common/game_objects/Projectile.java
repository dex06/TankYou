package fr.unice.miage.common.game_objects;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.geom.Vector2;
import fr.unice.miage.common.sprite.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Projectile {
    Sprite projectileSprite;
    String projectileName;

    protected Player player;
    protected Vector2 position;
    protected Vector2 velocity;
    protected Vector2 acceleration;
    protected double rotation;
    protected double width;
    protected double height;


    public Projectile(Player player, Vector2 position, Vector2 velocity, Sprite projectileSprite, String projectileName){
        this.player = player;
        this.position = position;
        this.velocity = velocity;
        this.projectileSprite = projectileSprite;
        this.projectileName = projectileName;
    }

    public void move(){
        position.add(velocity);
    }

    public void draw(CanvasGUI canvas){

        double x = this.position.getX();
        double y = this.position.getY();
        GraphicsContext gc = canvas.getGraphicsContext();
        gc.setFill(Color.BLACK);
        gc.fillRect(x, y, width, height);
//        gc.setFill(Color.BLACK);
//        gc.setFont(Font.font("Arial", 18));
//        gc.fillText(playerID, x+width/4, y+height/1.2);

    }

    public Sprite getSprite() { return projectileSprite; }
    public String getName() { return projectileName; }

    // Methods for position x and y
    public double getX(){ return position.getX(); }
    public void setX(double x){ position = new Vector2(x, position.getY()); }
    public double getY(){ return position.getY(); }
    public void setY(double y){ position = new Vector2(position.getX(), y); }

    // Methods for speedX and speedY
    public void setSpeedX(double x){ velocity = new Vector2(x, velocity.getY()); }
    public void setSpeedY(double y){ velocity = new Vector2(velocity.getX(), y);}
    public double getSpeedX(){ return velocity.getX(); }
    public double getSpeedY(){ return velocity.getY(); }
    public void reverseSpeed(){ velocity = new Vector2(-velocity.getX(), -velocity.getY()); }

    /* Vectors */
    // Methods for position vectors
    public Vector2 getPosition(){ return position;}
    public void setPosition(Vector2 v){ position = v;}
    public void addPosition(Vector2 v){ position.add(v); }

    // Methods for velocity vectors
    public Vector2 getVelocity() { return velocity; }
    public void setVelocity(Vector2 v){ velocity = v; }
    public void addVelocity(Vector2 v){ velocity.add(v); }

    // Methods for acceleration vectors
    public Vector2 getAcceleration(){ return acceleration; }
    public void setAcceleration(Vector2 v) {  acceleration = v; }
    public void addAcceleration(Vector2 v){ acceleration.add(v); }

    // Methods for rotation value
    public void setRotation(double rot) { rotation = rot; }
    public double getRotation(){ return rotation; }


    public void collidedWith(Projectile projectile) {
    }
}