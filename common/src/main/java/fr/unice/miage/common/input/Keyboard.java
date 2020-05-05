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
                System.out.println(event);
                switch (event.getCode()) {
                    case DOWN:
                        System.out.println("down pressed");
                        btnState.down = true;
                        break;
                    case UP:
                        System.out.println("up pressed");
                        btnState.up = true;
                        break;
                    case LEFT:
                        System.out.println("left pressed");
                        btnState.left = true;
                        break;
                    case RIGHT:
                        System.out.println("right pressed");
                        btnState.right = true;
                        break;
                    case SPACE:
                        System.out.println("Shot pressed");
                        btnState.shot = true;
                        break;
                }
            }
        });

        c.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case DOWN:
                        System.out.println("down released");
                        btnState.down = false;
                        break;
                    case UP:
                        System.out.println("up released");
                        btnState.up = false;
                        break;
                    case LEFT:
                        System.out.println("left released");
                        btnState.left = false;
                        break;
                    case RIGHT:
                        System.out.println("right released");
                        btnState.right = false;
                        break;
                    case SPACE:
                        System.out.println("Shot released");
                        btnState.shot = false;
                        break;
                }
            }
        });
    }
}
