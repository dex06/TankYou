package fr.unice.miage.common.sprite;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.geom.Vector2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public class ObstacleSprite extends Sprite {

    private final Vector2 position;
    private Image imgSprite = null;
    private double rotation = 0;
    private String shapeType;

    public ObstacleSprite(Vector2 position, double width, double height, Paint color, String shapeType) {
        super(width, height, color);
        this.position = position;
        this.rotation = 0;
        this.shapeType = shapeType;
    }

    public ObstacleSprite(Vector2 position, double width, double height, Paint color, Image img, String shapeType) {
        super(width, height, color);
        this.position = position;
        this.imgSprite = img;
        this.rotation = 0;
    }

    public ObstacleSprite(Vector2 position, double width, double height, Paint color, Image img, String shapeType, double rotation) {
        super(width, height, color);
        this.position = position;
        this.imgSprite = img;
        this.rotation = rotation;
    }

    public void draw(CanvasGUI canvas){
        GraphicsContext gc = canvas.getGraphicsContext();
        gc.save();
        if(imgSprite == null){
            gc.setFill(color);
            gc.translate(this.position.getX(), this.position.getY());
            gc.transform(new Affine(new Rotate(rotation, this.getWidth()/2, this.getHeight()/2)));
            gc.fillRect(0, 0, width, height);
        }
        else{
            gc.translate(this.position.getX(), this.position.getY());
            gc.transform(new Affine(new Rotate(rotation, this.getWidth()/2, this.getHeight()/2)));
            gc.drawImage(imgSprite, 0, 0, width, height);
        }
        gc.restore();
    }

    @Override
    public void setRotation(double rot) {
        rotation = rot;
    }

    @Override
    public double getRotation(){ return rotation; }

    @Override
    public Shape getBoundingShape() {
        if(shapeType == "rectangle") return new Rectangle(position.getX(), position.getY(), width, height);
        return new Circle(position.getX(), position.getY(), width);

    }
}
