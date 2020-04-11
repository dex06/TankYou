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
		this.stage.setTitle("Demo de jeu");
		this.root = new Group();
		this.theScene = new Scene(root);
		this.stage.setScene(theScene);
		root.getChildren().add(this.canvas.getCanvas());
		this.stage.sizeToScene();
	}
	public void start(){
		this.stage.show();
	}

	public void stop(){
		this.stage.close();
	}

	public void addSprite(Sprite p) {
		this.list.add(p);
	}

	public void addHealthBar(FlowPane healthBar){
		this.root.getChildren().remove(healthBar);
		this.root.getChildren().add(healthBar);
	}

	public Iterator<Sprite> spriteIterator() {
		return list.iterator();
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}


}
