package fr.unice.miage.sprite;


import fr.unice.miage.utils.Randomizer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public abstract class Sprite {

	private double width;
	private double height;
	private Paint[] colors = new Paint[] { Color.RED, Color.CYAN, Color.DARKCYAN };
	private Paint color;


	public Sprite(double width, double height, Paint color) {
		super();
		this.width = width;
		this.height = height;
		this.color = color;
	}

	public double getWidth(){ return width; }

	public double getHeight(){ return height; }

	public abstract Shape getBoundingShape() ;

	public void setRandomColor(){
		color = colors[(int) Randomizer.getRandomDoubleInRange(0,colors.length-1)];
	}
	public Paint[] getColors(){
		return colors;
	}



}
