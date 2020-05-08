package fr.unice.miage.common.input;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.game_objects.Player;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

public class Mouse {

    public void handleMouseEvent(Player player, CanvasGUI canvas, ButtonState btnState){
        Canvas c = canvas.getCanvas();
        c.setFocusTraversable(true);
        c.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btnState.shot = true;
            }
        });
        c.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btnState.shot = false;
            }
        });

    }


}
