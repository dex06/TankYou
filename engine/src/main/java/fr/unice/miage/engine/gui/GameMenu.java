package fr.unice.miage.engine.gui;

import fr.unice.miage.common.Repository;
import fr.unice.miage.engine.GameEngine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GameMenu  {
    private final GameEngine gameEngine;
    private final Stage stage;
    private final Repository repository;
    private final double width;
    private final double height;
    private int nbGUI = 0;
    private int nbPlayers = 1;
    private final int maxPlayers = 8;
    private final VBox guis = new VBox();
    private final VBox configs = new VBox();
    private final VBox players = new VBox();
    private CheckBox realPlayerCheckBox = new CheckBox();
    private final Label realPlayerLabel = new Label("IA Joueur 1");
    private boolean hasRealPlayerPlugin = false;
    private boolean hasRealPlayer = false;
    private final Font labelFont = new Font("Arial", 18);
    private List<String> listOfGUI1Options = new ArrayList<>();
    private List<String> listOfGUI2Options = new ArrayList<>();
    private List<List<String>> listOfPlayersOptions = new ArrayList<>();
    private List<String> listOfConfigOptions = new ArrayList<>();
    private List<String> listOfRealPlayerOptions = new ArrayList<>();


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
        reinitializeListsOfOptions();
        Label guiLabel = new Label("GUI");
        guiLabel.setFont(labelFont);
        HBox guiHBox = new HBox();
        VBox gui1 = createGUI1VBox();
        VBox gui2 = createGUI2VBox();
        guiHBox.getChildren().addAll(gui1, gui2);
        guiHBox.setSpacing(15);
        guiHBox.setAlignment(Pos.CENTER);
        guis.getChildren().addAll(guiLabel,guiHBox);

        Label configLabel = new Label("Configs");
        configLabel.setFont(labelFont);
        HBox configHBox = new HBox();
        VBox collVBox = createCollisionVbox();
        collVBox.setAlignment(Pos.CENTER);
        VBox obstacleVBox = createObstacleVBox();
        obstacleVBox.setAlignment(Pos.CENTER);
        VBox backgroundVBox = createBackgroundVBox();
        backgroundVBox.setAlignment(Pos.CENTER);
        configHBox.getChildren().addAll(collVBox, obstacleVBox, backgroundVBox);
        configHBox.setSpacing(15);
        configHBox.setAlignment(Pos.CENTER);


        configs.getChildren().addAll(configLabel,configHBox);



        Label playersLabel = new Label("Joueurs");
        playersLabel.setFont(labelFont);
        players.getChildren().add(playersLabel);

        Button addPlayerBtn = new Button("Ajouter un joueur");
        Button startGameBtn = new Button("Commencer la partie");
        startGameBtn.setDisable(true);
        addPlayerBtn.setOnAction(e -> {
            if(nbPlayers < maxPlayers) {
                nbPlayers++;
                addPlayerConfig();
                if(nbPlayers > 1) startGameBtn.setDisable(false);
                if(nbPlayers >= maxPlayers) addPlayerBtn.setDisable(true);
            }
        });
        //Ajouter un joueur de base
        if(repository.getRealPlayerPluginsNames().size() > 0) {
            listOfRealPlayerOptions= repository.getRealPlayerPluginsNames();
            hasRealPlayerPlugin = true;
        }
        addPlayerConfig();

        startGameBtn.setOnAction(e -> {
            listOfGUI1Options = getGUI1Options();
            listOfGUI2Options = getGUI2Options();
            listOfPlayersOptions = getPlayersOptions();
            listOfConfigOptions = getConfigOptions();
            try {
                startGame();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (NoSuchMethodException ex) {
                ex.printStackTrace();
            } catch (InstantiationException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            }
        });
        HBox btnHB = new HBox();
        btnHB.getChildren().addAll(addPlayerBtn, startGameBtn);
        btnHB.setSpacing(30);
        btnHB.setAlignment(Pos.BOTTOM_CENTER);
        BorderPane bottom = new BorderPane();
        bottom.setCenter(btnHB);

        VBox mainVBox = new VBox();
        mainVBox.getChildren().addAll(guis, configs, players);
        mainVBox.setSpacing(10);
        mainVBox.setPadding(new Insets(25));

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(25));
        root.setCenter(mainVBox);
        root.setBottom(bottom);
        root.setBackground(new Background(new BackgroundFill(Color.web("#e0e4ec"), null, null)));

        Scene scene = new Scene(root);
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.setWidth(512);
        stage.setHeight(512);
        stage.centerOnScreen();
        //stage.sizeToScene();
        //stage.show();
    }

    private void reinitializeListsOfOptions() {
        gameEngine.resetListOfPlayers();
        nbGUI = 0;
        nbPlayers = 1;
        hasRealPlayerPlugin = false;
        hasRealPlayer = false;
        listOfGUI1Options = new ArrayList<>();
        listOfGUI2Options = new ArrayList<>();
        listOfPlayersOptions = new ArrayList<>();
        listOfConfigOptions = new ArrayList<>();
        listOfRealPlayerOptions = new ArrayList<>();
    }


    private void addPlayerConfig(){
        HBox playerHBox = new HBox();
        Label playerLabel;

        VBox playerMovementVBox = createPlayerVBox("Mouvements");
        playerMovementVBox.setAlignment(Pos.CENTER);
        VBox playerWeaponVBox = createPlayerVBox("Armes");
        playerWeaponVBox.setAlignment(Pos.CENTER);
        VBox playerGraphicVBox = createPlayerVBox("Graphiques");
        playerGraphicVBox.setAlignment(Pos.CENTER);

        if(hasRealPlayerPlugin & nbPlayers == 1){
            VBox labelVBox = new VBox(realPlayerLabel);
            labelVBox.setAlignment(Pos.CENTER);
            realPlayerCheckBox = new CheckBox();
            Label checkBoxLabel = new Label("IH");
            checkBoxLabel.setAlignment(Pos.CENTER);
            VBox realPlayerVBox = new VBox(checkBoxLabel, realPlayerCheckBox);
            realPlayerVBox.setAlignment(Pos.CENTER);
            EventHandler<ActionEvent> event = e -> {
                    if (realPlayerCheckBox.isSelected()) {
                        realPlayerLabel.setText("IH joueur 1");
                        realPlayerLabel.setTextFill(Color.GREEN);
                        hasRealPlayer = true;
                    } else {
                        realPlayerLabel.setText("IA joueur 1");
                        realPlayerLabel.setTextFill(Color.BLACK);
                        hasRealPlayer = false;
                    }
            };
            realPlayerCheckBox.setOnAction(event);
            playerHBox.getChildren().addAll(labelVBox, playerMovementVBox, playerWeaponVBox, playerGraphicVBox, realPlayerVBox);
        } else {
            playerLabel = new Label("IA Joueur " + nbPlayers);
            VBox labelVBox = new VBox(playerLabel);
            labelVBox.setAlignment(Pos.CENTER);
            playerHBox.getChildren().addAll(labelVBox, playerMovementVBox, playerWeaponVBox, playerGraphicVBox);
        }
        playerHBox.setSpacing(15);
        playerHBox.setAlignment(Pos.CENTER_LEFT);
        players.getChildren().add(playerHBox);
        stage.sizeToScene();
    }

    private VBox createCollisionVbox() {
        VBox collVBox = new VBox();
        Label collLabel = new Label("Collision");
        ObservableList<String> opts = repository.getCollisionPluginsNames();
        if(!opts.contains("Aucun")) opts.add("Aucun");
        ComboBox collCB = new ComboBox(opts);
        System.out.println(opts);
        if(opts.size() > 0) collCB.setValue(opts.get(0));
        collVBox.getChildren().addAll(collLabel, collCB);
        return collVBox;
    }

    private VBox createObstacleVBox(){
        VBox obsVBox = new VBox();
        Label obsLabel = new Label("Obstacles");
        ObservableList<String> opts = repository.getObstaclePluginsNames();
        if(!opts.contains("Aucun")) opts.add("Aucun");
        ComboBox obsCB = new ComboBox(opts);
        System.out.println(opts);
        if(opts.size() > 0) obsCB.setValue(opts.get(0));
        obsVBox.getChildren().addAll(obsLabel, obsCB);
        return obsVBox;
    }
    private VBox createBackgroundVBox(){
        VBox bgVBox = new VBox();
        Label bgLabel = new Label("Aire de jeu");
        ObservableList<String> opts = repository.getBackgroundPluginsNames();
        if(!opts.contains("Aucun")) opts.add("Aucun");
        ComboBox bgCB = new ComboBox(opts);
        System.out.println(opts);
        if(opts.size() > 0) bgCB.setValue(opts.get(0));
        bgVBox.getChildren().addAll(bgLabel, bgCB);
        return bgVBox;
    }

    private VBox createGUI1VBox(){
        VBox guiVBox = new VBox();
        Label guiLabel = new Label("PlugIn " + ++nbGUI);
        ObservableList<String> opts = FXCollections.observableArrayList(repository.getGui1PluginsNames());
        if(!opts.contains("Aucun")) opts.add("Aucun");
        ComboBox guiCB = new ComboBox(opts);
        if(opts.size() > 0) guiCB.setValue(opts.get(0));
        guiVBox.getChildren().addAll(guiLabel, guiCB);
        guiVBox.setSpacing(10);
        guiVBox.setAlignment(Pos.CENTER);
        return guiVBox;
    }
    private VBox createGUI2VBox(){
        VBox guiVBox = new VBox();
        Label guiLabel = new Label("PlugIn " + ++nbGUI);
        ObservableList<String> opts = FXCollections.observableArrayList(repository.getGui2PluginsNames());
        if(!opts.contains("Aucun")) opts.add("Aucun");
        ComboBox guiCB = new ComboBox(opts);
        if(opts.size() > 0) guiCB.setValue(opts.get(0));
        guiVBox.getChildren().addAll(guiLabel, guiCB);
        guiVBox.setSpacing(10);
        guiVBox.setAlignment(Pos.CENTER);
        return guiVBox;
    }

    private VBox createPlayerVBox(String type){
        VBox playerVBox = new VBox();
        Label playerLabel = new Label(type);
        ObservableList<String> opts = FXCollections.observableArrayList();
        switch(type){
            case "Mouvements":
                opts = repository.getMovePluginsNames();
                break;
            case "Armes":
                opts = repository.getWeaponPluginsNames();
                break;
            case "Graphiques":
                opts = repository.getGraphicPluginsNames();
                break;
        }
        if(!opts.contains("Aucun")) opts.add("Aucun");
        ComboBox playerCB = new ComboBox(opts);
        System.out.println(opts);
        if(opts.size() > 0) playerCB.setValue(opts.get(0));
        playerVBox.getChildren().addAll(playerLabel, playerCB);
        return playerVBox;
    }

    private void startGame() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        stop();
        gameEngine.startGame(listOfGUI1Options, listOfGUI2Options, listOfConfigOptions, listOfPlayersOptions, listOfRealPlayerOptions, hasRealPlayer);
    }

    private List<String> getConfigOptions(){
        List<String> optionsList = new ArrayList<>();
        Set<Node> collSet = configs.lookupAll("ComboBox");
        for(Node cb : collSet){
            ComboBox new_cb = (ComboBox) cb;
            optionsList.add((String)new_cb.getValue());
        }
        return optionsList;
    }

    private List<String> getGUI1Options(){
        List<String> optionsList = new ArrayList<>();
        VBox gui1VBox = (VBox) guis.lookupAll("VBox").toArray()[1];
        Set<Node> CBSet =   gui1VBox.lookupAll("ComboBox");
        for(Node cb : CBSet){
            ComboBox new_cb = (ComboBox) cb;
            optionsList.add((String)new_cb.getValue());
        }
        return optionsList;
    }

    private List<String> getGUI2Options(){
        List<String> optionsList = new ArrayList<>();
        VBox gui2VBox = (VBox) guis.lookupAll("VBox").toArray()[2];
        Set<Node> CBSet =   gui2VBox.lookupAll("ComboBox");
        for(Node cb : CBSet){
            ComboBox new_cb = (ComboBox) cb;
            optionsList.add((String)new_cb.getValue());
        }
        return optionsList;
    }


    private List<List<String>> getPlayersOptions(){
        List<String> optionsList = new ArrayList<>();
        Set<Node> CBSet = players.lookupAll("ComboBox");
        for(Node cb : CBSet){
            ComboBox new_cb = (ComboBox) cb;
            optionsList.add((String)new_cb.getValue());
        }
        int nbPluginByPlayer = optionsList.size() / nbPlayers;
        List<List<String>> listOfOptionsByPlayer = new ArrayList<>();
        for(int i = 0; i < optionsList.size() - 1; i += nbPluginByPlayer){
            List<String> subL = optionsList.subList(i, i + nbPluginByPlayer);
            listOfOptionsByPlayer.add(subL);
        }
         return listOfOptionsByPlayer;
    }
}
