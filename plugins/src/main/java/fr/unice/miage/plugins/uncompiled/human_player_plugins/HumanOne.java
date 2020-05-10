package fr.unice.miage.plugins.uncompiled.human_player_plugins;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.game_objects.Player;
import fr.unice.miage.common.geom.Vector2;
import fr.unice.miage.common.input.ButtonState;
import fr.unice.miage.common.input.Keyboard;
import fr.unice.miage.common.input.Mouse;
import fr.unice.miage.common.plugins.PlugInRealPlayer;

public class HumanOne implements PlugInRealPlayer {

    public void handleKeyboard(Player player, CanvasGUI canvas, ButtonState btnState){
        Keyboard keyboard = new Keyboard();
        keyboard.handleKeyboardEvent(canvas, btnState);
    }

    public void handleMouse(Player player, CanvasGUI canvas, ButtonState btnState){
        Mouse.handleMouseEvent(canvas, btnState);
    }

    public void handleKeyInput(Player player, ButtonState btnState) {
        int dx = 0;
        int dy = 0;

        if(btnState.up) dy -= 1;
        if(btnState.down) dy += 1;
        if(btnState.left) dx -= 1;
        if(btnState.right) dx += 1;
        if(btnState.shot) { player.shoot(); }

        player.addPosition(new Vector2(dx,dy));
        if(player.isOutOfBorders()) player.addPosition(new Vector2(-dx,-dy));
        else player.setMovingDistance(new Vector2(dx, dy).length());
    }
}
