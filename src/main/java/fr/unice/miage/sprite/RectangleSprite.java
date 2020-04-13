package fr.unice.miage.sprite;

import fr.unice.miage.game.gui.CanvasGUI;
import fr.unice.miage.game_objects.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;

public class RectangleSprite extends Sprite {

	protected Player player;
	protected String playerID;

	public RectangleSprite(Player player, double width, double height, Paint color) {
		super(width, height, color);
		this.player = player;
		this.playerID = String.valueOf(player.getPlayerID());
	}
	public void draw(CanvasGUI canvas){
		double x = player.getPosition().getX();
		double y = player.getPosition().getY();
		GraphicsContext gc = canvas.getGraphicsContext();
		gc.setFill(color);
		gc.fillRect(x, y, width, height);
		gc.setFill(Color.BLACK);
		gc.setFont(Font.font("Arial", 18));
		gc.fillText(playerID, x+width/4, y+height/1.2);
	}

	@Override
	public Shape getBoundingShape() {
		double x = player.getPosition().getX();
		double y = player.getPosition().getY();
		return new Rectangle(x,y,width, height);
	}
}
