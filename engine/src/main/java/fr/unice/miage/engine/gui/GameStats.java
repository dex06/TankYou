package fr.unice.miage.engine.gui;

import fr.unice.miage.common.game_objects.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;


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
        txt.setFont(Font.font(30));
        winnerHB.getChildren().add(txt);
        winnerHB.setAlignment(Pos.CENTER);
        root.setTop(winnerHB);
    }

    public void setStats(List<Player> players){
        VBox statVBox = new VBox();
        HBox headers = new HBox();
        Label names = new Label("Names");
        Label shots = new Label("Nb of shots");
        Label distances = new Label("Distance");
        headers.getChildren().addAll(names, shots, distances);
        headers.setSpacing(30);
        statVBox.getChildren().add(headers);
        for(Player player : players){
            HBox playerHBox = new HBox();
            Label name = new Label(player.getName());
            Label shot = new Label(String.valueOf(player.getNumberOfShots()));
            long dist = Math.round(player.getMovingDistance());
            Label distance = new Label(String.valueOf(dist));
            playerHBox.getChildren().addAll(name, shot, distance);
            playerHBox.setSpacing(30);
            statVBox.getChildren().add(playerHBox);
        }
        //statVBox.setSpacing(20);
        statVBox.setAlignment(Pos.CENTER);
        root.setCenter(statVBox);
        root.setAlignment(statVBox, Pos.CENTER);
    }

    public void start(){
        stage.show();
    }
}
