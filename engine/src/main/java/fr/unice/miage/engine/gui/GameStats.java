package fr.unice.miage.engine.gui;

import fr.unice.miage.common.game_objects.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class GameStats {

    protected Stage stage;
    protected double width;
    protected double height;
    protected BorderPane root;

    public GameStats(Stage stage, double width, double height){
        this.stage = stage;
        this.width = width;
        this.height = height;
        this.root = new BorderPane();
    }

    public void init(boolean hasWinner, boolean hasStats){
        root.setPadding(new Insets(25));
        Scene scene = new Scene(root);
        stage.setTitle("Statistiques");
        stage.setScene(scene);
        stage.setWidth(width);
        stage.setHeight(height);
        stage.centerOnScreen();
    }

    public void setWinner(Player player){
        HBox winnerHB = new HBox();
        Text txt = new Text("The winner is " + player.getName());
        winnerHB.getChildren().add(txt);
        winnerHB.setAlignment(Pos.CENTER);
        root.setTop(winnerHB);
    }

    public void start(){
        stage.show();
    }
}
