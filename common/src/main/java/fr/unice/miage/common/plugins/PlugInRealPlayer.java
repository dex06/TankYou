package fr.unice.miage.common.plugins;

import fr.unice.miage.common.game_objects.Player;
import fr.unice.miage.common.input.ButtonState;
import javafx.stage.Stage;

public interface PlugInRealPlayer extends PlugIn {

    void handleKeyboard(Player player, Stage stage, ButtonState btnState);

    void handleMouse(Player player, Stage stage, ButtonState btnState);

    void handleKeyInput(Player player, ButtonState btnState);
}
