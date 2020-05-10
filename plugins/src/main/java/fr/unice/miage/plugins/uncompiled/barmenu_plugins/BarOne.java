package fr.unice.miage.plugins.uncompiled.barmenu_plugins;

import fr.unice.miage.common.Config;
import fr.unice.miage.common.plugins.PlugInGUI1;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


public class BarOne implements PlugInGUI1 {

    public HBox createBar() {

        HBox barHBox = new HBox();

        Button playBtn = new Button();

        Image playImg= new Image(BarOne.class.getClassLoader().getResourceAsStream("/barMenuIcons/playBtn.png"));
        ImageView playImgView = new ImageView(playImg);
        playImgView.setFitWidth(30);
        playImgView.setFitHeight(30);
        Image pauseImg = new Image(BarOne.class.getClassLoader().getResourceAsStream("/barMenuIcons/pauseBtn.png"));
        ImageView pauseImgView = new ImageView(pauseImg);
        pauseImgView.setFitWidth(30);
        pauseImgView.setFitHeight(30);
        playBtn.setGraphic(pauseImgView);


        //playBtn.setGraphic(new ImageView(pauseImg));
        playBtn.setOnAction(e -> {
            if(playBtn.getGraphic().equals(playImgView)){
                //playBtn.setText("Pause");
                playBtn.setGraphic(pauseImgView);
                Config.setPlay();
            }
            else {
                //playBtn.setText("Play");
                playBtn.setGraphic(playImgView);
                Config.setPause();
            }
        });
        Button stopBtn = new Button();
        Image stopImg = new Image(BarOne.class.getClassLoader().getResourceAsStream("/barMenuIcons/stopBtn.png"));
        ImageView stopImgView = new ImageView(stopImg);
        stopImgView.setFitWidth(30);
        stopImgView.setFitHeight(30);
        stopBtn.setGraphic(stopImgView);
        stopBtn.setOnAction(e -> {
            Config.setStop();
        });
        Button restartBtn = new Button();
        Image restartImg = new Image(BarOne.class.getClassLoader().getResourceAsStream("/barMenuIcons/skipPreviousBtn.png"));
        ImageView restartImgView = new ImageView(restartImg);
        restartImgView.setFitWidth(30);
        restartImgView.setFitHeight(30);
        restartBtn.setGraphic(restartImgView);
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
