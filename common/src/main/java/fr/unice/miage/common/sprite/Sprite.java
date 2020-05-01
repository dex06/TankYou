package fr.unice.miage.common.sprite;


import fr.unice.miage.common.utils.Randomizer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public abstract class Sprite {

	protected double width;
	protected double height;
	protected Paint[] colors = new Paint[] { Color.RED, Color.CYAN, Color.DARKCYAN, Color.TURQUOISE, Color.WHEAT };
	protected Paint color;


	public Sprite(double width, double height, Paint color) {
		super();
		this.width = width;
		this.height = height;
		this.color = color;
	}

	public double getWidth(){ return width; }

	public double getHeight(){ return height; }

	public abstract Shape getBoundingShape() ;

	public void setColor(Paint new_color){ color = new_color; }

	public void setRandomColor(){
		color = colors[Randomizer.getRandomIntInRange(0,colors.length)];
	}

	public Paint[] getColors(){
		return colors;
	}




}
