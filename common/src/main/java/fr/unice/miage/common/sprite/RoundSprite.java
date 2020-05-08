package fr.unice.miage.common.sprite;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.game_objects.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;

public class RoundSprite extends Sprite {

	private Player player;
	private double diameter;
	private double width;
	private double height;
	private Color color;
	private boolean displayId;

	public RoundSprite(Player player, double diameter, Color color, boolean displayId) {
		super(diameter, diameter, color);
		this.player = player;
		this.diameter = this.width = this.height = diameter;
		this.color = color;
		this.displayId = displayId;
	}

	public void draw(CanvasGUI canvas){
		double x = player.getPosition().getX();
		double y = player.getPosition().getY();
		GraphicsContext gc = canvas.getGraphicsContext();
		Paint save = gc.getFill();
		gc.setFill(color);
		gc.strokeOval(x, y, diameter, diameter);
		gc.fillOval(x, y, diameter, diameter);
		if(displayId) {
			gc.setFill(Color.BLACK);
			String playerID = String.valueOf(player.getPlayerID());
			gc.setFont(Font.font("Arial", 18));
			gc.fillText(playerID, x + diameter / 4, y + diameter / 1.2);
		}
	}

	@Override
	public Shape getBoundingShape() {
		double x = player.getPosition().getX();
		double y = player.getPosition().getY();
		return new Rectangle(x, y, diameter, diameter);
	}
}
