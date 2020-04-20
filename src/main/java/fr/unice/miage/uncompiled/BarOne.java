package fr.unice.miage.uncompiled;

import fr.unice.miage.game.Config;
import fr.unice.miage.plugins.PlugInGUI1;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;



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

        HBox barVBox = new HBox();

        //Image playImg = new Image(getClass().getResourceAsStream("../sprite/img/playBtn.png"));
        //Image pauseImg = new Image(getClass().getResourceAsStream("../sprite/img/pauseBtn.png"));
        //Button playBtn = new Button("Play", new ImageView(playImg));
        Button playBtn = new Button("Pause");
        //playBtn.setGraphic(new ImageView(playImg));

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
        onMouseEvents(playBtn);
        onMouseEvents(stopBtn);



        barVBox.getChildren().addAll(playBtn, stopBtn);
        barVBox.setSpacing(10);
        barVBox.setAlignment(Pos.CENTER);
        return barVBox;
    }

    private void onMouseEvents(Button btn){
        DropShadow shadow = new DropShadow();
        btn.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        btn.setEffect(shadow);
                    }
                });
        btn.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        btn.setEffect(null);
                    }
                });
    }



    public static void main(String[] args) {
            launch(args);
    }
}
