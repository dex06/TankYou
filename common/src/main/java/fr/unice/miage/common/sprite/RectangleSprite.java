package fr.unice.miage.common.sprite;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.game_objects.Player;
import fr.unice.miage.common.utils.Randomizer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public class RectangleSprite extends Sprite {

	protected Player player;
	protected boolean displayId;
	protected double rotation = 0;

	public RectangleSprite(Player player, double width, double height, Paint color, boolean displayId) {
		super(width, height, color);
		this.player = player;
		this.displayId = displayId;
	}
	@Override
	public void draw(CanvasGUI canvas){
		double x = player.getX();
		double y = player.getY();
		GraphicsContext gc = canvas.getGraphicsContext();
		gc.save();
		gc.translate(x,y);
		gc.transform(new Affine(new Rotate(rotation, this.getWidth()/2, this.getHeight()/2)));
		gc.setFill(color);
		gc.fillRect(0, 0, width, height);
		if(displayId) {
			gc.setFill(Color.BLACK);
			String playerID = String.valueOf(player.getPlayerID());
			gc.setFont(Font.font("Arial", 18));
			gc.fillText(playerID, width / 4, height / 1.2);
		}
		gc.restore();
	}

	@Override
	public void setRotation(double rot) { rotation = rot; }

	public double getRotation(){ return rotation; }

	@Override
	public Shape getBoundingShape() {
		double x = player.getX();
		double y = player.getY();
		return new Rectangle(x,y,width, height);
	}

	@Override
	public void setRandomColor(){
		color = colors[Randomizer.getRandomIntInRange(0,colors.length)];
	}


}
