package fr.unice.miage.game;

import fr.unice.miage.common.Config;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application
{
    @Override
    public void start(Stage stage) {
        Config.setTesting(false);
        if(Config.getTesting()) System.out.println("Testing mode");
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
