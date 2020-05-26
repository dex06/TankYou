package fr.unice.miage.common.sprite;


import fr.unice.miage.common.CanvasGUI;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public abstract class Sprite {
	protected double rotation = 0;
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

	}

	public Paint[] getColors(){
		return colors;
	}

	public void draw(CanvasGUI canvas){

	}


    public void setRotation(double rot) { rotation = rot; };

	public double getRotation(){ return rotation; }
}
