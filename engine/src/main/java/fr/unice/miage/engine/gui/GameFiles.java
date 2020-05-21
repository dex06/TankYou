package fr.unice.miage.engine.gui;

import fr.unice.miage.common.Config;
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
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;

public class GameFiles extends Application {

    private VBox selectorVBox;
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
        String userDirectoryString = System.getProperty("user.home");
        File userDirectory = new File(userDirectoryString);
        if(!userDirectory.canRead()) {
            userDirectory = new File("c:/");
        }
        fileChooser.setInitialDirectory(userDirectory);

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Jar Files", "*.jar")
        );

        Label txtFldLabel = new Label("Enter path to jar");
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
        Button chooserBtn = new Button("Import Jar File");
        chooserBtn.setOnAction(e -> {
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if(selectedFile != null) {
                if (selectedFile.getName().endsWith(".jar")) {
                    try {
                        repository.loadLibraries(selectedFile.getCanonicalPath());
                        displayRepos();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
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
        HBox copyHBox = new HBox(copyBtn);
        selectHBox.getChildren().addAll(txtFldLabel, fieldHBox, okHBox, chooserHBox, copyHBox);
        selectHBox.setPadding(new Insets(10,10,10,10));
        selectHBox.setSpacing(10);
        selectHBox.setAlignment(Pos.TOP_CENTER);

        selectorVBox = new VBox();


        displayRepos();
        VBox mainVBox = new VBox();

        FlowPane root = new FlowPane();
        root.setPadding(new Insets(10,10,10,10));
        root.getChildren().addAll(mainVBox);
        Button gotoMenuBtn = new Button("Configuration Menu");
        gotoMenuBtn.setOnAction(e -> {

            GameEngine gameEngine = new GameEngine(primaryStage, Config.getWorldWidth(),Config.getWorldHeight(), repository);
            primaryStage.close();
            gameEngine.initMenu();
            gameEngine.startMenu();
        });
        mainVBox.getChildren().addAll(selectHBox, selectorVBox, gotoMenuBtn);
        Scene scene = new Scene(root, 960, 600);


        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void displayRepos() {
        List<File> jarFiles = repository.getJarFiles();
        for (File jar : jarFiles) {
            CheckBox checkBox = new CheckBox(jar.getName());
            selectorVBox.getChildren().add(checkBox);
        }

        //selectorVBox.setStyle("-fx-background-color: #336699;");
    }
}

