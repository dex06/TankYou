package fr.unice.miage.game;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameMenu extends Application {
    private Stage stage;
    private int width;
    private int height;
    private int nbGUI = 0;
    private int nbPlayers = 0;
    private VBox guis = new VBox();
    private VBox players = new VBox();

    /*public GameMenu() {
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
        VBox mainVBox = new VBox();

        Label guiLabel = new Label("GUI");
        HBox guiHBox = new HBox();
        VBox gui1 = this.createGUIVBox();
        VBox gui2 = this.createGUIVBox();
        guiHBox.getChildren().addAll(gui1, gui2);
        this.guis.getChildren().addAll(guiLabel,guiHBox);

        Label playersLabel = new Label("Joueurs");
        this.players.getChildren().add(playersLabel);

        Button addPlayerBtn = new Button("Ajouter un joueur");
        addPlayerBtn.setOnAction(e -> {
            this.nbPlayers++;
            this.addPlayerConfig();
        });

        mainVBox.getChildren().addAll(this.guis, this.players, addPlayerBtn);

        Group root = new Group();
        Scene scene = new Scene(mainVBox, 512, 512);
        this.stage.setTitle("Menu");
        this.stage.setScene(scene);
        Canvas canvas = new Canvas(512, 512);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        this.stage.sizeToScene();
        this.stage.show();
    }
    private void addPlayerConfig(){
        HBox playerHBox = new HBox();
        Label playerLabel = new Label("Joueur " + this.nbPlayers);
        VBox playerMovementVBox = this.createPlayerVBox("Mouvements");
        VBox playerWeaponVBox = this.createPlayerVBox("Armes");
        VBox playerGraphicVBox = this.createPlayerVBox("Graphiques");
        playerHBox.getChildren().addAll(playerLabel, playerMovementVBox, playerWeaponVBox, playerGraphicVBox);
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
        guiVBox.getChildren().addAll(guiLabel, new ComboBox(options));
        guiVBox.setAlignment(Pos.CENTER_LEFT);
        return guiVBox;
        //guiConfigList.add(guiVBox);
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
        playerVBox.getChildren().addAll(playerLabel, new ComboBox(options));
        return playerVBox;
        //guiConfigList.add(playerVBox);
       /* comboBox.getItems().addAll(
                "Option 4",
                "Option 5",
                "Option 6"
        );*/
    }

    public static void main(String[] args) {
       launch(args);


    }
}
