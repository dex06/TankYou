package fr.unice.miage.engine.gui;

import fr.unice.miage.common.Repository;
import fr.unice.miage.engine.GameEngine;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;

public class GameFiles extends Application {

    private VBox selectorVBox;
    private HBox pluginsHBox;
    private final List<Repository> repoList = new ArrayList<>();
    private Repository repository;
    private final List<String> listOfGUI1Options = new ArrayList<>();
    private final List<String> listOfGUI2Options = new ArrayList<>();
    private final List<List<String>> listOfPlayersOptions = new ArrayList<>();
    private final List<String> listOfConfigOptions = new ArrayList<>();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //repoList.add(new Repository());
        repository = new Repository();

        primaryStage.setTitle("Plugin selection");

        FileChooser fileChooser = new FileChooser();
        DirectoryChooser directoryChooser = new DirectoryChooser();

        String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
        File currentFilePath = new File(currentPath);
        String userDirectoryString = System.getProperty("user.home");
        File userDirectory = new File(userDirectoryString);
        if(!userDirectory.canRead()) {
            userDirectory = new File("c:/");
        }


        fileChooser.setInitialDirectory(currentFilePath);
        FileChooser.ExtensionFilter jarFilter = new FileChooser.ExtensionFilter("Jar or Zip Files", "*.jar", "*.zip");

        fileChooser.getExtensionFilters().addAll(jarFilter);
        fileChooser.setSelectedExtensionFilter(jarFilter);

        directoryChooser.setTitle("Select directory");
        directoryChooser.setInitialDirectory(userDirectory);


        Label txtFldLabel = new Label("Enter path to jar, zip or dir");
        TextField textField = new TextField();
        textField.setMinWidth(400);
        Button okBtn = new Button("OK");
        okBtn.setFocusTraversable(false);
        okBtn.setOnAction(e -> {
            String path = textField.getText();
            try {
                if(new File(path).exists()) {
                    //Repository repo = new Repository(path);
                    repository.loadLibraries(path);
                    displayRepos();
                }
            } catch(Exception err){
                System.err.println(err);
            }
        });
        Button chooserBtn = new Button("Select Archive");
        chooserBtn.setOnAction(e -> {
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if(selectedFile != null) {
                textField.setText(selectedFile.getAbsolutePath());
                if (selectedFile.getName().endsWith(".jar") || selectedFile.getName().endsWith(".zip")) {
                    try {
                        repository.loadLibraries(selectedFile.getCanonicalPath());
                        displayRepos();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            } else {
                textField.setText(null);
            }
        });

        Button dirChooserBtn = new Button("Select Directory");
        dirChooserBtn.setOnAction(e -> {
            File dir = directoryChooser.showDialog(primaryStage);
            if (dir != null) {
                textField.setText(dir.getAbsolutePath());
                try {
                    repository.loadLibraries(dir.getCanonicalPath());
                    displayRepos();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            } else {
                textField.setText(null);
            }
        });

        Button copyBtn = new Button("Copy Jar File");
        copyBtn.setOnAction(e -> {
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if(selectedFile != null) {
                if (selectedFile.getName().endsWith(".jar")) {
                    try {
                        JarFile jar = new JarFile(selectedFile);
                        Repository.copyJarFile(jar, new File("plugins repository"));
                        repository.loadLibraries(selectedFile.getCanonicalPath());
                        displayRepos();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });
        HBox selectHBox = new HBox();
        HBox fieldHBox = new HBox(textField);
        HBox okHBox = new HBox(okBtn);
        HBox chooserHBox = new HBox(chooserBtn);
        HBox dirChooserHBox = new HBox(dirChooserBtn);
        HBox copyHBox = new HBox(copyBtn);
        selectHBox.getChildren().addAll(txtFldLabel, fieldHBox, okHBox, chooserHBox, dirChooserHBox, copyHBox);
        selectHBox.setPadding(new Insets(10,10,10,10));
        selectHBox.setSpacing(10);
        selectHBox.setAlignment(Pos.TOP_CENTER);

        selectorVBox = new VBox();
        pluginsHBox = new HBox();

        displayRepos();
        VBox mainVBox = new VBox();


        Button gotoMenuBtn = new Button("Configuration Menu");
        gotoMenuBtn.setOnAction(e -> {

            GameEngine gameEngine = new GameEngine(primaryStage, repository);
            primaryStage.close();
            gameEngine.initMenu();
            gameEngine.startMenu();
        });


        BorderPane pluginsPane = new BorderPane();
        selectorVBox.setAlignment(Pos.CENTER);
        selectorVBox.setPadding(new Insets(20));
        pluginsPane.setTop(selectorVBox);
        pluginsHBox.setAlignment(Pos.CENTER);
        pluginsPane.setCenter(pluginsHBox);



        BorderPane goToMenuPane = new BorderPane();
        gotoMenuBtn.setAlignment(Pos.CENTER);
        goToMenuPane.setCenter(gotoMenuBtn);



        mainVBox.getChildren().addAll(selectHBox);

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10,10,10,10));
        root.setTop(mainVBox);
        root.setCenter(pluginsPane);
        root.setBottom(goToMenuPane);


        Scene scene = new Scene(root, 960, 600);


        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void displayRepos() {
        List<File> jarFiles = repository.getJarFiles();
        Label archiveLabel = new Label("Archives");
        archiveLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        for (File jar : jarFiles) {
            if(selectorVBox.getChildren().isEmpty()) selectorVBox.getChildren().add(archiveLabel);

            CheckBox checkBox = new CheckBox(jar.getName());
            checkBox.setSelected(true);
            selectorVBox.getChildren().add(checkBox);
        }
        if(!pluginsHBox.getChildren().isEmpty()) pluginsHBox = new HBox();
        if(!jarFiles.isEmpty()) displayPlugins();
        selectorVBox.setSpacing(5);
        //selectorVBox.setStyle("-fx-background-color: #336699;");
    }

    public void displayPlugins(){
        int spacing = 5;
        String font = "Verdana";
        int fontSize = 12;

        VBox moveVBox = new VBox();
        moveVBox.setSpacing(spacing);
        VBox graphicVBox = new VBox();
        graphicVBox.setSpacing(spacing);
        VBox weaponVBox = new VBox();
        weaponVBox.setSpacing(spacing);
        VBox collisionVBox = new VBox();
        collisionVBox.setSpacing(spacing);
        VBox obstacleVBox = new VBox();
        obstacleVBox.setSpacing(spacing);
        VBox backgroundVBox = new VBox();
        backgroundVBox.setSpacing(spacing);
        VBox gui1VBox = new VBox();
        gui1VBox.setSpacing(spacing);
        VBox gui2VBox = new VBox();
        gui2VBox.setSpacing(spacing);
        VBox realPlayerVBox = new VBox();
        realPlayerVBox.setSpacing(spacing);

        for(String name : repository.getMovePluginsNames()) {
            Label moveLabel = new Label("Moves");
            moveLabel.setFont(Font.font(font, FontWeight.BOLD, fontSize));
            if(moveVBox.getChildren().isEmpty()) moveVBox.getChildren().add(moveLabel);
            CheckBox checkBox = new CheckBox(name);
            checkBox.setSelected(true);
            moveVBox.getChildren().add(checkBox);
        }
        for(String name : repository.getGraphicPluginsNames()) {
            Label graphicLabel = new Label("Graphics");
            graphicLabel.setFont(Font.font(font, FontWeight.BOLD, fontSize));
            if(graphicVBox.getChildren().isEmpty()) graphicVBox.getChildren().add(graphicLabel);
            CheckBox checkBox = new CheckBox(name);
            checkBox.setSelected(true);
            graphicVBox.getChildren().add(checkBox);
        }
        for(String name : repository.getWeaponPluginsNames()) {
            Label weaponLabel = new Label("Weapons");
            weaponLabel.setFont(Font.font(font, FontWeight.BOLD, fontSize));
            if(weaponVBox.getChildren().isEmpty()) weaponVBox.getChildren().add(weaponLabel);
            CheckBox checkBox = new CheckBox(name);
            checkBox.setSelected(true);
            weaponVBox.getChildren().add(checkBox);
        }
        for(String name : repository.getCollisionPluginsNames()) {
            Label collisionLabel = new Label("Collisions");
            collisionLabel.setFont(Font.font(font, FontWeight.BOLD, fontSize));
            if(collisionVBox.getChildren().isEmpty()) collisionVBox.getChildren().add(collisionLabel);
            CheckBox checkBox = new CheckBox(name);
            checkBox.setSelected(true);
            collisionVBox.getChildren().add(checkBox);
        }
        for(String name : repository.getObstaclePluginsNames()) {
            Label obstacleLabel = new Label("Obstacles");
            obstacleLabel.setFont(Font.font(font, FontWeight.BOLD, fontSize));
            if(obstacleVBox.getChildren().isEmpty()) obstacleVBox.getChildren().add(obstacleLabel);
            CheckBox checkBox = new CheckBox(name);
            checkBox.setSelected(true);
            obstacleVBox.getChildren().add(checkBox);
        }
        for(String name : repository.getBackgroundPluginsNames()) {
            Label backgroundLabel = new Label("Backgrounds");
            backgroundLabel.setFont(Font.font(font, FontWeight.BOLD, fontSize));
            if(backgroundVBox.getChildren().isEmpty()) backgroundVBox.getChildren().add(backgroundLabel);
            CheckBox checkBox = new CheckBox(name);
            checkBox.setSelected(true);
            backgroundVBox.getChildren().add(checkBox);
        }
        for(String name : repository.getGui1PluginsNames()) {
            Label gui1Label = new Label("GUI1s");
            gui1Label.setFont(Font.font(font, FontWeight.BOLD, fontSize));
            if(gui1VBox.getChildren().isEmpty()) gui1VBox.getChildren().add(gui1Label);
            CheckBox checkBox = new CheckBox(name);
            checkBox.setSelected(true);
            gui1VBox.getChildren().add(checkBox);
        }
        for(String name : repository.getGui2PluginsNames()) {
            Label gui2Label = new Label("GUI2s");
            gui2Label.setFont(Font.font(font, FontWeight.BOLD, fontSize));
            if(gui2VBox.getChildren().isEmpty()) gui2VBox.getChildren().add(gui2Label);
            CheckBox checkBox = new CheckBox(name);
            checkBox.setSelected(true);
            gui2VBox.getChildren().add(checkBox);
        }
        for(String name : repository.getRealPlayerPluginsNames()) {
            Label realPlayerLabel = new Label("Real Player");
            realPlayerLabel.setFont(Font.font(font, FontWeight.BOLD, fontSize));
            if(realPlayerVBox.getChildren().isEmpty()) realPlayerVBox.getChildren().add(realPlayerLabel);
            CheckBox checkBox = new CheckBox(name);
            checkBox.setSelected(true);
            realPlayerVBox.getChildren().add(checkBox);
        }

        pluginsHBox.getChildren().addAll(moveVBox, graphicVBox, weaponVBox, backgroundVBox, obstacleVBox, collisionVBox, gui1VBox, gui2VBox, realPlayerVBox);
        pluginsHBox.setSpacing(10);

    }
}

