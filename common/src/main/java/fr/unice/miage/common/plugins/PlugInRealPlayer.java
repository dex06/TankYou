package fr.unice.miage.common.plugins;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.game_objects.Player;
import fr.unice.miage.common.input.ButtonState;

public interface PlugInRealPlayer extends PlugIn {

    void handleKeyboard(Player player, CanvasGUI canvas, ButtonState btnState);

    void handleMouse(Player player, CanvasGUI canvas, ButtonState btnState);

    void handleKeyInput(Player player, ButtonState btnState);
}
