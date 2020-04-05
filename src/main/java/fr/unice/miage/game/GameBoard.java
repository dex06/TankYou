package fr.unice.miage.game;

import fr.unice.miage.game.gui.CanvasGUI;
import fr.unice.miage.sprite.Sprite;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;

public class GameBoard {

	private Stage stage;
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
		Group root = new Group();
		Scene theScene = new Scene(root);
		this.stage.setScene(theScene);
		root.getChildren().add(this.canvas.getCanvas());
	}
	public void start(){
		this.stage.show();
	}

	
	public void addSprite(Sprite p) {
		this.list.add(p);
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

	private void checkForCollision(Sprite s, Iterator<Sprite> it) {
		while (it.hasNext()) {
			Sprite d = it.next();
			if (d != s) {
				if (s.getBoundingShape().getBoundsInParent().intersects(d.getBoundingShape().getBoundsInParent())) {
					System.out.println(" it's a crash !!!");
					s.handleCollision(this, d);
				}
			}
		}
	}

}
