package fr.unice.miage.engine.gui;

import fr.unice.miage.common.game_objects.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
        Text txt = new Text("The winner is " + player.getPlayerName());
        txt.setFont(Font.font(40));
        winnerHB.getChildren().add(txt);
        winnerHB.setAlignment(Pos.CENTER);
        root.setTop(winnerHB);
    }

    public void setStats(List<Player> players){


        TableView<Player> table = new TableView<>();

        //Names
        TableColumn<Player, String> nameCol //
                = new TableColumn<>("Player's name");

        //Shots
        TableColumn<Player, Integer> shotCol//
                = new TableColumn<>("Nb of shots");

        //Distance
        TableColumn<Player, Integer> distCol//
                = new TableColumn<>("Distance");

        nameCol.setCellValueFactory(new PropertyValueFactory<>("playerName"));
        nameCol.setMinWidth(200);
        shotCol.setCellValueFactory(new PropertyValueFactory<>("numberOfShots"));
        shotCol.setMinWidth(200);
        distCol.setCellValueFactory(new PropertyValueFactory<>("movingDistance"));
        distCol.setMinWidth(200);

        ObservableList<Player> list = FXCollections.observableArrayList(players);
        table.setItems(list);

        table.getColumns().addAll(nameCol, shotCol, distCol);


        stage.setTitle("Game statistics");
        root.setPadding(new Insets(5));
        root.setCenter(table);
        //root.setAlignment(table, Pos.CENTER);
    }

    public void start(){
        stage.show();
    }
}
