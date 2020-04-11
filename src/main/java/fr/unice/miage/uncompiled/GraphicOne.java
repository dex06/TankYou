package fr.unice.miage.uncompiled;

import fr.unice.miage.game.gui.CanvasGUI;
import fr.unice.miage.game.gui.HealthBar;
import fr.unice.miage.game_objects.Player;
import fr.unice.miage.plugins.PlugInGraphic;
import fr.unice.miage.sprite.RectangleSprite;
import fr.unice.miage.sprite.Sprite;
import javafx.scene.paint.Color;

public class GraphicOne implements PlugInGraphic {
    //private CanvasGUI canvas;
    private Player player;
    private String name = "rectangle";
    private RectangleSprite playerSprite;
    private HealthBar healthBar;
    private double width = 20;
    private double height = 20;
    private Color color = Color.RED;

    public void init(Player player){
        this.player = player;
        this.playerSprite = new RectangleSprite(player, width, height, color);
        this.playerSprite.setRandomColor();
        this.healthBar = new HealthBar(70, 20, Color.GREEN);
    }

    public void draw(Player player, CanvasGUI canvas) {
        this.playerSprite.draw(canvas);
        this.healthBar.draw(player, canvas);
    }

    public String getName(){
        return this.name;
    }

    public Color getRandomColor(){
        return this.playerSprite.getColors()[(int) Math.random() * 3];
    }

    public Sprite getPlayerSprite(){
        return this.playerSprite;
    }

}
