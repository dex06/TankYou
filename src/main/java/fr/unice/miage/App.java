package fr.unice.miage;

import fr.unice.miage.game.GameEngine;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application
{
    @Override
    public void start(Stage stage) {
        GameEngine gameEngine = new GameEngine(stage, 600,600, "plugins");
        gameEngine.initMenu();
        gameEngine.startMenu();
    }
    public static void main( String[] args )
    {
        launch(args);
    }
}
