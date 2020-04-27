package fr.unice.miage.uncompiled;

import fr.unice.miage.Config;
import fr.unice.miage.plugins.PlugInGUI1;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


public class BarOne implements PlugInGUI1 {

    public HBox createBar(){

        HBox barHBox = new HBox();

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
        //onMouseEvents(playBtn);
        //onMouseEvents(stopBtn);

        Text chrono = new Text("00.00:00");


        barHBox.getChildren().addAll(playBtn, stopBtn, chrono);
        barHBox.setSpacing(10);
        barHBox.setAlignment(Pos.CENTER);
        return barHBox;
    }

    /*private void onMouseEvents(Button btn){
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
    }*/

}
