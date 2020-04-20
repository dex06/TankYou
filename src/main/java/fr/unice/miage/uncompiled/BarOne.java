package fr.unice.miage.uncompiled;

import fr.unice.miage.game.Config;
import fr.unice.miage.plugins.PlugInGUI1;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;

public class BarOne extends Application implements PlugInGUI1 {

    @Override
    public void start(Stage stage) {
        BorderPane root= new BorderPane();
        root.setCenter(createBar());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setWidth(512);
        stage.setHeight(100);
        stage.show();
    }
    public HBox createBar(){
        File file = new File("src/main/java/fr/unice/miage/sprite/img/playBtn.png");
        HBox barVBox = new HBox();
        //Image imageOk = new Image(getClass().getResourceAsStream(file));
        //Button playBtn = new Button("Play", new ImageView(imageOk));
        Button playBtn = new Button("Pause");

        playBtn.setOnAction(e -> {
            if(playBtn.getText().equals("Play")){
                playBtn.setText("Pause");
                Config.setPlay();
            }
            else {
                playBtn.setText("Play");
                Config.setPause();
            }
        });
        Button stopBtn = new Button("Stop");
        stopBtn.setOnAction(e -> {
            Config.setStop();
        });



        barVBox.getChildren().addAll(playBtn, stopBtn);
        barVBox.setAlignment(Pos.CENTER);
        return barVBox;
    }



    public static void main(String[] args) {
            launch(args);
    }
}
