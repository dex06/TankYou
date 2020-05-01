package fr.unice.miage.common.game_objects;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.geom.Vector2;
import fr.unice.miage.common.sprite.Sprite;

import java.util.List;

public class Projectile {
    Sprite projectileSprite;
    String projectileName;

    protected Vector2 position;
    protected Vector2 sizeRect;
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
        this.sizeRect = new Vector2(4,4);
        this.velocity = 10;
    }

    public void move(){
        this.position.setX(this.position.getX() + this.velocity*Math.cos(this.rotation));
        this.position.setY(this.position.getY() + this.velocity*Math.sin(this.rotation));
    }

    /*public void draw(CanvasGUI canvas){

        double x = this.position.getX();
        double y = this.position.getY();
        GraphicsContext gc = canvas.getGraphicsContext();
        gc.setFill(Color.BLACK);
        gc.fillRect(x, y, this.sizeRect.getX(), this.sizeRect.getX());
//        gc.setFill(Color.BLACK);
//        gc.setFont(Font.font("Arial", 18));
//        gc.fillText(playerID, x+width/4, y+height/1.2);

    }

    public Sprite getSprite(){
        return this.projectileSprite;
    }

    public String getName(){ return this.projectileName; }

    public void collidedWith(Projectile projectile) {

    }*/

    //TODO adapté avec une classe sprite et utiliser les fonctions dans checkPlayerToPlayerCollision
    //mal codé
    public boolean checkCollisionsWithPlayer(List<Player> players){
            for(int j = 0; j < players.size(); j++){
                if(this.position.getX() > players.get(j).getPosition().getX()
                && this.position.getX() < players.get(j).getPosition().getX() + players.get(j).getSprite().getWidth()
                && this.position.getY() > players.get(j).getPosition().getY()
                && this.position.getY() < players.get(j).getPosition().getY() + players.get(j).getSprite().getHeight())
                {
                    System.out.println("Collision projectile");
                    players.get(j).setHealth(players.get(j).getHealth()-10);
                    return true;
                }
            }
            return false;
    }

    public void draw(CanvasGUI canvas) {
    }

    public Sprite getSprite() {
        return projectileSprite;
    }

    public Vector2 getPosition() {
        return position;
    }

    public double getVelocity() {
        return velocity;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double v) {
        rotation = v;
    }

    public void collidedWith(Projectile projectile) {
    }

    public String getName() {
        return projectileName;
    }
}
