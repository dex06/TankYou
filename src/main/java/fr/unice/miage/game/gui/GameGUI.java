package fr.unice.miage.game.gui;

import fr.unice.miage.sprite.RectangleSprite;
import fr.unice.miage.sprite.RoundSprite;
import fr.unice.miage.sprite.Sprite;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Iterator;

public class GameGUI{
	private long lastUpdateNanoTime;
	private GameBoard board;
	private GameMenu menu;


	public void start(Stage stage) {
		initMenu(stage);
		//initGame(stage);
	}

	private void initMenu(Stage stage){
		//menu = new GameMenu(stage);
		menu.start();
		//Stage menuStage = menu.getStage();
	}

	private void initGame(Stage stage) {

		stage.setTitle("Demo de jeu");
		Group root = new Group();
		Scene theScene = new Scene(root);
		stage.setScene(theScene);
		CanvasGUI canvas = new CanvasGUI(512, 512);
		//root.getChildren().add(canvas);
		GraphicsContext gc = canvas.getGraphicsContext();
		stage.sizeToScene();

		//board = new GameBoard(512, 512, canvas);

		RectangleSprite r = new RectangleSprite(50, 10, 0, 100);
		board.addSprite(r);
		RoundSprite rs = new RoundSprite(50, 200, 0, -100, 20);
		board.addSprite(rs);

		//taken from https://gamedevelopment.tutsplus.com/tutorials/introduction-to-javafx-for-game-development--cms-23835
		lastUpdateNanoTime = System.nanoTime();
		//c'est notre boucle de jeu principale
		new AnimationTimer() {
			public void handle(long currentNanoTime) {
				double t = (currentNanoTime - lastUpdateNanoTime) / 1000000000.0;

				gc.setFill(Color.AZURE);
				gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
				gc.setFill(Color.BLACK);

				Iterator<Sprite> it = board.spriteIterator();
				while (it.hasNext()) {
					Sprite s = it.next();
					s.update(t, board);
					checkForCollision(s, board.spriteIterator());
					s.render(gc);
				}
				lastUpdateNanoTime = currentNanoTime;
			}
		}.start();
		stage.show();
	}

	private void checkForCollision(Sprite s, Iterator<Sprite> it) {
		while (it.hasNext()) {
			Sprite d = it.next();
			if (d != s) {
				if (s.getBoundingShape().getBoundsInParent().intersects(d.getBoundingShape().getBoundsInParent())) {
					System.out.println(" it's a crash !!!");
					s.handleCollision(board, d);
				}
			}
		}
	}




	public static void main(String[] args) {

	}
}
