package fr.unice.miage.engine;

import fr.unice.miage.common.Config;
import fr.unice.miage.engine.gui.GameFiles;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application
{
    @Override
    public void start(Stage stage) throws Exception {
        Config.setTesting(false);
        if(Config.getTesting()) System.out.println("Testing mode");
        Config.setWorldWidth(600);
        Config.setWorldHeight(600);
        GameFiles gameFiles = new GameFiles();
        gameFiles.start(stage);
        /*GameEngine gameEngine = new GameEngine(stage, Config.getWorldWidth(),Config.getWorldHeight(), "C:\\Users\\marqu\\IdeaProjects\\pa-huet\\plugins repository");
        gameEngine.initMenu();
        gameEngine.startMenu();*/
    }
    public static void main( String[] args )
    {
        launch(args);
    }
}
