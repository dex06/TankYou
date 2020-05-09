package fr.unice.miage.engine.gui;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.Config;
import fr.unice.miage.common.Repository;
import fr.unice.miage.common.plugins.PlugInBackground;
import fr.unice.miage.common.plugins.PlugInGUI1;
import fr.unice.miage.common.sprite.Sprite;
import fr.unice.miage.common.utils.Timer;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;

public class GameBoard {

	private final Stage stage;
	private Scene theScene;
	private Group root;
	private HBox canvasBox;
	private PlugInGUI1 bar;
	private HBox barMenu;
	private final int width;
	private final int height;
	private CanvasGUI canvas;
	private final Repository repository;

	private final ArrayList<Sprite> list = new ArrayList<>();
	
	public GameBoard(Stage stage, int width, int height, CanvasGUI canvas, Repository repository) {
		super();
		this.stage = stage;
		this.width = width;
		this.height = height;
		this.canvas = canvas;
		this.repository = repository;
	}
	public void init(boolean hasBarMenu) {

		stage.setTitle("Demo de jeu");
		BorderPane root = new BorderPane();
		theScene = new Scene(root);
		stage.setScene(theScene);
		canvasBox = new HBox(canvas.getCanvas());
		canvasBox.setMinWidth(Config.getWorldWidth());
		canvasBox.setMinHeight(Config.getWorldHeight());
		root.setCenter(canvasBox);
		//root.getChildren().add(canvas.getCanvas());
		if(hasBarMenu) {
			String gui1ClassName = repository.getGui1PluginsNames().get(0);
			try { bar = repository.loadGUI1(gui1ClassName); }
			catch(Exception e){ System.err.println(e); }
			barMenu = bar.createBar();
			barMenu.setAlignment(Pos.BOTTOM_CENTER);
			barMenu.setFocusTraversable(false);
			root.setBottom(barMenu);
		}
		stage.sizeToScene();

	}
	public void start(){
		stage.show();
	}

	public void stop(){
		stage.close();
	}

	public void setTimer(){
		String timeStr = Timer.chronoToString();
		Text text = (Text) barMenu.lookup("Text");
		text.setText(timeStr);
	}
	public void setBackground(PlugInBackground bg){
		Background newBg = bg.createBackground();
		canvasBox.setBackground(newBg);
	}

	public CanvasGUI getCanvas(){ return canvas; }
	public void setCanvas(CanvasGUI canvas) { this.canvas = canvas; }

	public Scene getScene(){ return theScene; }

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
