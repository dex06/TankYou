package fr.unice.miage.plugins.uncompiled;

import fr.unice.miage.common.Config;
import fr.unice.miage.common.plugins.PlugInGUI1;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


public class BarOne implements PlugInGUI1 {

    public HBox createBar(){

        HBox barHBox = new HBox();

        Button playBtn = new Button("Pause");

        //Image playImg= new Image(MyClassLoader.class.getResourceAsStream("/src/main/resources/barMenuIcons/playImg.png"));
        //Image pauseImg = new Image(MyClassLoader.class.getResourceAsStream("/src/main/resources/barMenuIcons/pauseImg.png"));
        //playBtn.setGraphic(new ImageView(pauseImg));
        playBtn.setOnAction(e -> {
            if(playBtn.getText().equals("Play")){
                playBtn.setText("Pause");
                //playBtn.setGraphic(new ImageView(pauseImg));
                Config.setPlay();
            }
            else {
                playBtn.setText("Play");
                //playBtn.setGraphic(new ImageView(playImg));
                Config.setPause();
            }
        });
        Button stopBtn = new Button("Stop");
        //Image stopImg = new Image(MyClassLoader.class.getResourceAsStream("/src/resources/barMenuIcons/stopImg.png"));
        //stopBtn.setGraphic(new ImageView(stopImg));
        stopBtn.setOnAction(e -> {
            Config.setStop();
        });
        Button restartBtn = new Button("Restart");
        restartBtn.setOnAction(e -> {
            Config.setRestart();
        });

        Text chrono = new Text("00.00:00");


        barHBox.getChildren().addAll(playBtn, stopBtn, restartBtn, chrono);
        barHBox.setSpacing(10);
        barHBox.setAlignment(Pos.CENTER);
        return barHBox;
    }

}
