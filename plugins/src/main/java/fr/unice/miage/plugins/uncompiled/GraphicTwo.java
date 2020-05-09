package fr.unice.miage.plugins.uncompiled;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.game_objects.HealthBar;
import fr.unice.miage.common.game_objects.Player;
import fr.unice.miage.common.plugins.PlugInGraphic;
import fr.unice.miage.common.sprite.RoundSprite;
import fr.unice.miage.common.sprite.Sprite;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class GraphicTwo implements PlugInGraphic {
    //private CanvasGUI canvas;
    private Player player;
    private final String name = "cercle";
    private RoundSprite playerSprite;
    private HealthBar healthBar;
    private final double width = 20;
    private final double height = 20;
    private final Color color = Color.OLIVE;

    public void init(Player player){
        this.player = player;
        this.playerSprite = new RoundSprite(player, width, color, true);
        this.playerSprite.setRandomColor();
        this.healthBar = new HealthBar(30, 5, Color.GREEN);
    }

    public void draw(CanvasGUI canvas) {
        healthBar.draw(player, canvas);
        playerSprite.draw(canvas);
    }

    public String getName(){
        return name;
    }

    public Paint getRandomColor(){
        return playerSprite.getColors()[(int) (Math.random() * 3)];
    }

    public Sprite getPlayerSprite(){
        return playerSprite;
    }

    public void setToDead(){
        playerSprite.setColor(Color.GRAY);
    }

}
