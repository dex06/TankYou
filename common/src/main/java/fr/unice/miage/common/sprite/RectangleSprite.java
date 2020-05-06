package fr.unice.miage.common.sprite;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.game_objects.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;

public class RectangleSprite extends Sprite {

	protected Player player;
	protected boolean displayId;

	public RectangleSprite(Player player, double width, double height, Paint color, boolean displayId) {
		super(width, height, color);
		this.player = player;
		this.displayId = displayId;
	}

	public void draw(CanvasGUI canvas){
		double x = player.getX();
		double y = player.getY();
		GraphicsContext gc = canvas.getGraphicsContext();
		gc.setFill(color);
		gc.fillRect(x, y, width, height);
		gc.setFill(Color.BLACK);
		if(displayId) {
			String playerID = String.valueOf(player.getPlayerID());
			gc.setFont(Font.font("Arial", 18));
			gc.fillText(playerID, x + width / 4, y + height / 1.2);
		}
	}

	@Override
	public Shape getBoundingShape() {
		double x = player.getX();
		double y = player.getY();
		return new Rectangle(x,y,width, height);
	}
}
