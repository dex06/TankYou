package fr.unice.miage.classes;

import fr.unice.miage.game.gui.CanvasGUI;
import fr.unice.miage.plugins.PlugInGraphic;
import fr.unice.miage.sprite.RectangleSprite;
import fr.unice.miage.sprite.Sprite;

public class GraphicOne implements PlugInGraphic {
    //private CanvasGUI canvas;
    private RectangleSprite playerSprite;
    private double randX;
    private double randY;
    private double randSpeedX;
    private double randSpeedY;

    public GraphicOne(){
        this.init();
        this.playerSprite = new RectangleSprite(randX, randY,  randSpeedX, randSpeedY);
    }

    public void init(){
        this.randX = this.getRandX();
        this.randY = this.getRandY();
        this.randSpeedX = this.getRandSpeedX();
        this.randSpeedY = this.getRandSpeedY();
    }
    @Override
    public void draw(CanvasGUI canvas) {
        //this.playerSprite.update(t, board);
        //checkForCollision(s, board.spriteIterator());
        canvas.clean();
        this.playerSprite.render(canvas.getGraphicsContext());
    }

    public double getRandX(){
        return Math.random() * 50;
    }

    public double getRandY(){
        return Math.random() * 50;
    }

    public double getRandSpeedX(){
        return Math.random() * 10;
    }

    public double getRandSpeedY(){
        return Math.random() * 10;
    }

    public Sprite getPlayerSprite(){
        return this.playerSprite;
    }
}
