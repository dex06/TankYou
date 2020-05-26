package fr.unice.miage.common.sprite;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.game_objects.Player;
import fr.unice.miage.common.geom.Rotation;
import fr.unice.miage.common.geom.Vector2;
import fr.unice.miage.common.utils.Randomizer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public class RoundSprite extends Sprite {

	private final Player player;
	private final double diameter;
	private double rotation = 0;

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
		Vector2 transV = Rotation.getTransVecAfterRot(new Vector2(this.getWidth()/2,this.getHeight()/2), rotation);
		GraphicsContext gc = canvas.getGraphicsContext();
		gc.save();
		gc.setFill(color);
		gc.translate(x, y);
		gc.transform(new Affine(new Rotate(rotation, this.getWidth()/2, this.getHeight()/2)));
		gc.strokeOval(0, 0, diameter, diameter);
		gc.fillOval(0, 0, diameter, diameter);
		if(displayId) {
			gc.setFill(Color.BLACK);
			String playerID = String.valueOf(player.getPlayerID());
			gc.setFont(Font.font("Arial", 18));
			gc.fillText(playerID, diameter / 4, diameter / 1.2);
		}
		gc.restore();
	}

	@Override
	public void setRotation(double rot) {
		rotation = rot;
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
