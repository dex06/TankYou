package fr.unice.miage.plugins.uncompiled;

import fr.unice.miage.common.game_objects.Player;
import fr.unice.miage.common.plugins.PlugInRealPlayer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class HumanOne implements PlugInRealPlayer {

    public void handleKeyboardEvent(Player player, Scene scene){
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            System.out.println(event);
            switch (event.getCode()) {
                case DOWN:
                    System.out.println("down");
                    break;
                case UP:
                    System.out.println("up");
                    break;
            }
        });
    }

    public void handleMouseEvent(Player player, Scene scene){}
}
