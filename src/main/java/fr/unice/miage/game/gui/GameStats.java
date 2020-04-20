package fr.unice.miage.game.gui;

import javafx.stage.Stage;

public class GameStats {

    protected Stage stage;
    protected double width;
    protected double height;

    public GameStats(Stage stage, double width, double height){
        this.stage = stage;
        this.width = width;
        this.height = height;
    }

    public void init(boolean asWinner){
    }
}
