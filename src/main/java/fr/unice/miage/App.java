package fr.unice.miage;

import fr.unice.miage.game.Config;
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
        Config.setTesting(true);
        Config.setWorldWidth(600);
        Config.setWorldHeight(600);
        GameEngine gameEngine = new GameEngine(stage, Config.getWorldWidth(),Config.getWorldHeight(), "plugins");
        gameEngine.initMenu();
        gameEngine.startMenu();
    }
    public static void main( String[] args )
    {
        launch(args);
    }
}
