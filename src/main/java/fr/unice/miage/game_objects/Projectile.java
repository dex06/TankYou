package fr.unice.miage.game_objects;

import fr.unice.miage.game.gui.CanvasGUI;
import fr.unice.miage.geom.Vector2;
import fr.unice.miage.sprite.RectangleSprite;
import fr.unice.miage.sprite.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Projectile {
    Sprite projectileSprite;
    String projectileName;

    protected Vector2 position;
    protected double velocity;
    protected Vector2 acceleration;
    protected double rotation;

//    public Projectile(Sprite projectileSprite, String projectileName){
//        this.projectileSprite = projectileSprite;
//        this.projectileName = projectileName;
//    }

    public Projectile(Vector2 position, double rotation){
//        this.projectileSprite = new RectangleSprite();
        this.projectileName = "Proj test";
        this.position = position;
        this.rotation = rotation;
        this.velocity = 10;
    }

    public void move(){
        this.position.setX(this.position.getX() + this.velocity*Math.cos(this.rotation));
        this.position.setY(this.position.getY() + this.velocity*Math.sin(this.rotation));
    }

    public void draw(CanvasGUI canvas){

        double x = this.position.getX();
        double y = this.position.getY();
        GraphicsContext gc = canvas.getGraphicsContext();
        gc.setFill(Color.BLACK);
        gc.fillRect(x, y, 5, 5);
//        gc.setFill(Color.BLACK);
//        gc.setFont(Font.font("Arial", 18));
//        gc.fillText(playerID, x+width/4, y+height/1.2);

    }

    public Sprite getSprite(){
        return this.projectileSprite;
    }

    public String getName(){ return this.projectileName; }

    public void collidedWith(Projectile projectile) {

    }
}
