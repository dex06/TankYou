package fr.unice.miage.sprite;

import fr.unice.miage.game.gui.CanvasGUI;
import fr.unice.miage.game_objects.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.Random;

public class RectangleSprite extends Sprite {

	private Player player;
	private double width;
	private double height;
	private Paint color;
	private Paint[] colors = new Paint[] { Color.RED, Color.CYAN, Color.DARKCYAN };
	private int currentColor = 0; 
	
	private Random r = new Random();
	
	public RectangleSprite(Player player, double width, double height, Paint color) {
		super(width, height, color);
		this.player = player;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	public void draw(CanvasGUI canvas){
		double x = player.getPosition().getX();
		double y = player.getPosition().getY();
		GraphicsContext gc = canvas.getGraphicsContext();
		gc.setFill(color);
		gc.fillRect(x, y, width, height);
	}

	@Override
	public Shape getBoundingShape() {
		double x = player.getPosition().getX();
		double y = player.getPosition().getY();
		return new Rectangle(x,y,width, height);
	}

	public void setColor(int num){
		this.currentColor = num;
	}

	public Paint[] getColors(){
		return this.colors;
	}

}
