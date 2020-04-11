package fr.unice.miage.sprite;


import fr.unice.miage.utils.Randomizer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public abstract class Sprite {

	private double width;
	private double height;
	private Color[] colors = new Color[] { Color.RED, Color.CYAN, Color.DARKCYAN };
	private Color color;


	public Sprite(double width, double height, Color color) {
		super();
		this.width = width;
		this.height = height;
		this.color = color;

	}

	public double getWidth(){ return this.width; }

	public double getHeight(){ return this.height; }

	public abstract Shape getBoundingShape() ;

	public void setRandomColor(){
		this.color = colors[(int) Randomizer.getRandomDoubleInRange(0,this.colors.length-1)];
	}
	public Color[] getColors(){
		return this.colors;
	}



}
