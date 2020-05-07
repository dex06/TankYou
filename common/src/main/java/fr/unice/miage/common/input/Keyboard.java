package fr.unice.miage.common.input;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.game_objects.Player;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

public class Keyboard {

    public void handleKeyboardEvent(Player player, CanvasGUI canvas, ButtonState btnState){
        Canvas c = canvas.getCanvas();
        c.setFocusTraversable(true);
        c.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println(event.getCode());
                switch (event.getCode()) {
                    case Z:
                        btnState.up = true;
                        break;
                    case S:
                        btnState.down = true;
                        break;
                    case Q:
                        btnState.left = true;
                        break;
                    case D:
                        btnState.right = true;
                        break;
                    case SPACE:
                        btnState.shot = true;
                        break;
                }
            }
        });

        c.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case S:
                        btnState.up = false;
                        break;
                    case Z:
                        btnState.down = false;
                        break;
                    case Q:
                        btnState.left = false;
                        break;
                    case D:
                        btnState.right = false;
                        break;
                    case SPACE:
                        btnState.shot = false;
                        break;
                }
            }
        });
    }
}
