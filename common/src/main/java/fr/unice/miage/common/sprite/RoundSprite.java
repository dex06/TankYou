package fr.unice.miage.common.sprite;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.game_objects.Player;
import fr.unice.miage.common.utils.Randomizer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;

public class RoundSprite extends Sprite {

	private final Player player;
	private final double diameter;

	private final boolean displayId;

	public RoundSprite(Player player, double diameter, Color color, boolean displayId) {
		super(diameter, diameter, color);
		this.player = player;
		this.diameter = this.width = this.height = diameter;
		this.displayId = displayId;
	}
	@Override
	public void draw(CanvasGUI canvas){
		double x = player.getPosition().getX();
		double y = player.getPosition().getY();
		GraphicsContext gc = canvas.getGraphicsContext();
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

	@Override
	public void setRandomColor(){
		color = colors[Randomizer.getRandomIntInRange(0,colors.length)];
	}
}
