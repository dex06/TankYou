package fr.unice.miage;

import fr.unice.miage.game.GameEngine;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;

/**
 * Hello world!
 *
 */
public class App extends Application
{
    @Override
    public void start(Stage stage) {
        GameEngine gameEngine = new GameEngine(stage, 600,600, new File(""));
        gameEngine.init();
        gameEngine.start();
    }
    public static void main( String[] args )
    {
        launch(args);
    }
}
