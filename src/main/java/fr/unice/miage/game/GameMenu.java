package fr.unice.miage.game;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GameMenu extends Application {
    private Stage stage;
    private int width;
    private int height;
    private int nbGUI = 0;
    private int nbPlayers = 1;
    private VBox guis = new VBox();
    private VBox players = new VBox();
    private ArrayList<Object> listOfGUIOptions = new ArrayList<>();
    private ArrayList<Object> listOfPlayersOptions = new ArrayList<>();

    /*public GameMenu(int width, int height, Stage stage) {
        super();
        //this.stage = stage;
        //this.width = width;
        //this.height = height;
    }*/

    public Stage getStage() {
        return this.stage;
    }

    @Override
    public void start(Stage stage){
        init(stage);
    }

    private void init(Stage stage){
        this.stage = stage;

        Label guiLabel = new Label("GUI");
        guiLabel.setFont(new Font("Arial", 18));
        HBox guiHBox = new HBox();
        VBox gui1 = this.createGUIVBox();
        VBox gui2 = this.createGUIVBox();
        guiHBox.getChildren().addAll(gui1, gui2);
        guiHBox.setSpacing(15);
        guiHBox.setAlignment(Pos.CENTER);
        this.guis.getChildren().addAll(guiLabel,guiHBox);

        Label playersLabel = new Label("Joueurs");
        playersLabel.setFont(new Font("Arial", 18));
        this.players.getChildren().add(playersLabel);

        Button addPlayerBtn = new Button("Ajouter un joueur");
        addPlayerBtn.setOnAction(e -> {
            this.nbPlayers++;
            this.addPlayerConfig();
        });
        //Ajouter un joueur de base
        this.addPlayerConfig();

        Button startGameBtn = new Button("Commencer la partie");
        startGameBtn.setOnAction(e -> {
            this.listOfGUIOptions = this.getOptions("guis");
            this.listOfPlayersOptions = this.getOptions("players");
            this.startGame();
                });
        HBox btnHB = new HBox();
        btnHB.getChildren().addAll(addPlayerBtn, startGameBtn);
        btnHB.setSpacing(30);
        btnHB.setAlignment(Pos.BOTTOM_CENTER);
        BorderPane bottom = new BorderPane();
        bottom.setCenter(btnHB);

        VBox mainVBox = new VBox();
        mainVBox.getChildren().addAll(this.guis, this.players);
        mainVBox.setSpacing(10);
        mainVBox.setPadding(new Insets(25));

        BorderPane root= new BorderPane();
        root.setPadding(new Insets(25));
        root.setCenter(mainVBox);
        root.setBottom(bottom);
        root.setBackground(new Background(new BackgroundFill(Color.web("#e0e4ec"), null, null)));

        Scene scene = new Scene(root);
        this.stage.setTitle("Menu");
        this.stage.setScene(scene);
        this.stage.setWidth(512);
        this.stage.setHeight(512);
        this.stage.centerOnScreen();
        //this.stage.sizeToScene();
        this.stage.show();
    }
    private void addPlayerConfig(){
        HBox playerHBox = new HBox();
        Label playerLabel = new Label("Joueur " + this.nbPlayers);
        VBox playerMovementVBox = this.createPlayerVBox("Mouvements");
        playerMovementVBox.setAlignment(Pos.CENTER);
        VBox playerWeaponVBox = this.createPlayerVBox("Armes");
        playerWeaponVBox.setAlignment(Pos.CENTER);
        VBox playerGraphicVBox = this.createPlayerVBox("Graphiques");
        playerGraphicVBox.setAlignment(Pos.CENTER);
        playerHBox.getChildren().addAll(playerLabel, playerMovementVBox, playerWeaponVBox, playerGraphicVBox);
        playerHBox.setSpacing(10);
        playerHBox.setAlignment(Pos.CENTER_LEFT);
        players.getChildren().add(playerHBox);

    }

    private VBox createGUIVBox(){
        VBox guiVBox = new VBox();
        Label guiLabel = new Label("PlugIn " + ++this.nbGUI);
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Option 1",
                        "Option 2",
                        "Option 3"
                );
        ComboBox guiCB = new ComboBox(options);
        guiCB.setValue("Option 1");
        guiVBox.getChildren().addAll(guiLabel, guiCB);
        guiVBox.setSpacing(10);
        guiVBox.setAlignment(Pos.CENTER);
        return guiVBox;
    }

    private VBox createPlayerVBox(String type){
        VBox playerVBox = new VBox();
        Label playerLabel = new Label(type);
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Option 1",
                        "Option 2",
                        "Option 3"
                );
        ComboBox playerCB = new ComboBox(options);
        playerCB.setValue("Option 1");
        playerVBox.getChildren().addAll(playerLabel, playerCB);
        return playerVBox;
       /* comboBox.getItems().addAll(
                "Option 4",
                "Option 5",
                "Option 6"
        );*/
    }

    private void startGame(){
        //this.listOfPlayersOptions;
    }

    private ArrayList<Object> getOptions(String type){
        ArrayList<Object> optionsList = new ArrayList<>();
        Set<Node> CBSet =  type == "guis" ? this.guis.lookupAll("ComboBox") : this.players.lookupAll("ComboBox");
        for(Node cb : CBSet){
            ComboBox new_cb = (ComboBox) cb;
            optionsList.add((String)new_cb.getValue());
        }
        if(type == "players"){
            int nbPluginByPlayer = optionsList.size() / this.nbPlayers;
            ArrayList<Object> listOfOptionsByPlayer = new ArrayList<>();
            for(int i = 0; i < optionsList.size() - 1; i += nbPluginByPlayer){
                    List<Object> subL = optionsList.subList(i, i + nbPluginByPlayer);
                    listOfOptionsByPlayer.add(subL);
            }
            optionsList = listOfOptionsByPlayer;
        }
        return optionsList;
    }

    public static void main(String[] args) {
       launch(args);


    }
}
