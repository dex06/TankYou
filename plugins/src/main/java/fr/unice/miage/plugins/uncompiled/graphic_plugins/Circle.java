package fr.unice.miage.plugins.uncompiled.graphic_plugins;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.game_objects.Player;
import fr.unice.miage.common.plugins.PlugInGraphic;
import fr.unice.miage.common.sprite.RoundSprite;
import javafx.scene.paint.Color;

public class Circle implements PlugInGraphic {


    public void init(Player player){
        player.setSprite(new RoundSprite(player, 20, Color.OLIVE, true));
        player.setHealthBar(30, 5, Color.GREEN);
    }

    public void draw(Player player, CanvasGUI canvas) {
        player.getHealthBar().draw(player, canvas);
        RoundSprite sprite = (RoundSprite) player.getSprite();
        sprite.draw(canvas);
    }

    public void setToDead(Player player){
        player.getSprite().setColor(Color.GRAY);
    }

}
