package fr.unice.miage.game;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
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
import javafx.scene.text.Font;
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
        guiLabel.setFont(new Font("Arial", 18));
        HBox guiHBox = new HBox();
        VBox gui1 = this.createGUIVBox();
        VBox gui2 = this.createGUIVBox();
        guiHBox.getChildren().addAll(gui1, gui2);
        guiHBox.setAlignment(Pos.CENTER);
        this.guis.getChildren().addAll(guiLabel,guiHBox);

        Label playersLabel = new Label("Joueurs");
        playersLabel.setFont(new Font("Arial", 18));
        this.players.getChildren().add(playersLabel);

        Button addPlayerBtn = new Button("Ajouter un joueur");
        HBox btnHB = new HBox();
        btnHB.getChildren().add(addPlayerBtn);
        btnHB.setAlignment(Pos.BOTTOM_CENTER);
        addPlayerBtn.setOnAction(e -> {
            this.nbPlayers++;
            this.addPlayerConfig();
        });

        mainVBox.getChildren().addAll(this.guis, this.players, btnHB);
        mainVBox.setSpacing(10);
        mainVBox.setPadding(new Insets(25));

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
        ComboBox playerCB = new ComboBox(options);
        playerCB.setValue("Option 1");
        playerVBox.getChildren().addAll(playerLabel, playerCB);
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
