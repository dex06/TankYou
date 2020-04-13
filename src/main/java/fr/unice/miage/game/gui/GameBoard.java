package fr.unice.miage.game.gui;

import fr.unice.miage.sprite.Sprite;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;

public class GameBoard {

	private Stage stage;
	private Scene theScene;
	private Group root;
	private int width;
	private int height;
	private CanvasGUI canvas;

	private ArrayList<Sprite> list = new ArrayList<>();
	
	public GameBoard(Stage stage, int width, int height, CanvasGUI canvas) {
		super();
		this.stage = stage;
		this.width = width;
		this.height = height;
		this.canvas = canvas;
	}
	public void init(){
		stage.setTitle("Demo de jeu");
		root = new Group();
		theScene = new Scene(root);
		stage.setScene(theScene);
		root.getChildren().add(canvas.getCanvas());
		stage.sizeToScene();
	}
	public void start(){
		stage.show();
	}

	public void stop(){
		stage.close();
	}

	public void addSprite(Sprite p) {
		list.add(p);
	}

	public void addHealthBar(FlowPane healthBar){
		root.getChildren().remove(healthBar);
		root.getChildren().add(healthBar);
	}

	public Iterator<Sprite> spriteIterator() {
		return list.iterator();
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		height = height;
	}
}
