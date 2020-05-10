package fr.unice.miage.plugins.uncompiled.graphic_plugins;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.game_objects.Player;
import fr.unice.miage.common.plugins.PlugInGraphic;
import fr.unice.miage.common.sprite.RectangleSprite;
import javafx.scene.paint.Color;

public class Rectangle implements PlugInGraphic {


    public void init(Player player){
        player.setSprite(new RectangleSprite(player, 20, 20, Color.RED, true));
        player.setHealthBar(30, 5, Color.GREEN);
    }

    public void draw(Player player, CanvasGUI canvas) {
        player.getHealthBar().draw(player, canvas);
        RectangleSprite sprite = (RectangleSprite) player.getSprite();
        sprite.draw(canvas);
    }

    public void setToDead(Player player){
        player.getSprite().setColor(Color.GRAY);
    }

}
