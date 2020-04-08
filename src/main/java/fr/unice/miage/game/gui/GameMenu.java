package fr.unice.miage.game.gui;

import fr.unice.miage.game.GameEngine;
import fr.unice.miage.game.Repository;
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

public class GameMenu  {
    private GameEngine gameEngine;
    private Stage stage;
    private Repository repository;
    private double width;
    private double height;
    private int nbGUI = 0;
    private int nbPlayers = 1;
    private VBox guis = new VBox();
    private VBox players = new VBox();
    private List<String> listOfGUIOptions = new ArrayList<>();
    private List<List<String>> listOfPlayersOptions = new ArrayList<>();

    public GameMenu(GameEngine gameEngine, Stage stage, Repository repository) {
        super();
        this.gameEngine = gameEngine;
        this.stage = stage;
        this.repository = repository;
        this.width = stage.getWidth();
        this.height = stage.getHeight();
    }
    public void start(){
        this.stage.show();
    }

    public void stop(){
        this.stage.close();
    }

    public void init(){
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
            this.listOfGUIOptions = this.getGUIOptions();
            this.listOfPlayersOptions = this.getPlayersOptions();
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
        //this.stage.show();
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
        ObservableList<String> opts = FXCollections.observableArrayList();;
        switch(type){
            case "Mouvements":
                opts = this.repository.getMovePluginsNames();
                break;
            case "Armes":
                opts = this.repository.getWeaponPluginsNames();
                break;
            case "Graphiques":
                opts = this.repository.getGraphicPluginsNames();
                break;
        }
        ComboBox playerCB = new ComboBox(opts);
        if(opts.size() > 0) playerCB.setValue(opts.get(0));
        playerVBox.getChildren().addAll(playerLabel, playerCB);
        return playerVBox;
    }

    private void startGame(){
        this.stop();
        this.gameEngine.loadingPlayers(this.listOfPlayersOptions);
        this.gameEngine.createGameBoard();
    }

    private List<String> getGUIOptions(){
        List<String> optionsList = new ArrayList<>();
        Set<Node> CBSet =   this.guis.lookupAll("ComboBox");
        for(Node cb : CBSet){
            ComboBox new_cb = (ComboBox) cb;
            optionsList.add((String)new_cb.getValue());
        }
        return optionsList;
    }

    private List<List<String>> getPlayersOptions(){
        List<String> optionsList = new ArrayList<>();
        Set<Node> CBSet = this.players.lookupAll("ComboBox");
        for(Node cb : CBSet){
            ComboBox new_cb = (ComboBox) cb;
            optionsList.add((String)new_cb.getValue());
        }

        int nbPluginByPlayer = optionsList.size() / this.nbPlayers;
        List<List<String>> listOfOptionsByPlayer = new ArrayList<>();
        for(int i = 0; i < optionsList.size() - 1; i += nbPluginByPlayer){
            List<String> subL = optionsList.subList(i, i + nbPluginByPlayer);
            listOfOptionsByPlayer.add(subL);
        }
         return listOfOptionsByPlayer;
    }

    public static void main(String[] args) {



    }
}
