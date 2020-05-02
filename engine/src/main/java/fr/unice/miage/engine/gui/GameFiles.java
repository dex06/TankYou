package fr.unice.miage.engine.gui;

import fr.unice.miage.common.Repository;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GameFiles extends Application {

    private VBox selectorVBox;
    private List<Repository> repoList = new ArrayList<>();
    private Repository repository;
    private List<String> listOfGUI1Options = new ArrayList<>();
    private List<String> listOfGUI2Options = new ArrayList<>();
    private List<List<String>> listOfPlayersOptions = new ArrayList<>();
    private List<String> listOfConfigOptions = new ArrayList<>();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        repoList.add(new Repository());

        primaryStage.setTitle("Plugin selection");

        FileChooser fileChooser = new FileChooser();

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
                    Repository repo = new Repository(path);
                }
            } catch(Exception err){
                System.err.println(err);
            }
        });
        Button chooserBtn = new Button("Select Jar File");
        chooserBtn.setOnAction(e -> {
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if(selectedFile.getName().endsWith(".jar")){
                try {
                    Repository repo = new Repository(selectedFile.getCanonicalPath());
                    if(!repoList.contains(repo)) {
                        repoList.add(repo);
                        displayRepos();
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }
        });
        HBox selectHBox = new HBox();
        HBox fieldHBox = new HBox(textField);
        HBox okHBox = new HBox(okBtn);
        HBox chooserHBox = new HBox(chooserBtn);
        selectHBox.getChildren().addAll(txtFldLabel, fieldHBox, okHBox, chooserHBox);
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
            primaryStage.close();
        });
        mainVBox.getChildren().addAll(selectHBox, selectorVBox, gotoMenuBtn);
        Scene scene = new Scene(root, 960, 600);


        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void displayRepos(){
        for(Repository repo : repoList){
            List<File> jarFiles = repo.getJarFiles();
            for(File jar : jarFiles) {
                CheckBox checkBox = new CheckBox(jar.getName());
                selectorVBox.getChildren().add(checkBox);
            }
        }
    }
}

