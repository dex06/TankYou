package fr.unice.miage.sprite;

import fr.unice.miage.CanvasGUI;
import fr.unice.miage.game_objects.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class RoundSprite extends Sprite {

	private Player player;
	private double diameter;
	private Color color;

	public RoundSprite(Player player, double diameter, Color color) {
		super(diameter, diameter, color);
		this.player = player;
		this.diameter = diameter;
		this.color = color;
	}

	public void draw(CanvasGUI canvas){
		double x = player.getPosition().getX();
		double y = player.getPosition().getY();
		GraphicsContext gc = canvas.getGraphicsContext();
		Paint save = gc.getFill();
		gc.setFill(color);
		gc.strokeOval(x, y, diameter, diameter);
		gc.fillOval(x, y, diameter, diameter);
		gc.setFill(save);
	}

	@Override
	public Shape getBoundingShape() {
		double x = player.getPosition().getX();
		double y = player.getPosition().getY();
		return new Rectangle(x, y, diameter, diameter);
	}
}
