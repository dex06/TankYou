package fr.unice.miage.game;

import fr.unice.miage.game.gui.CanvasGUI;
import fr.unice.miage.game.gui.GameBoard;
import fr.unice.miage.game.gui.GameMenu;
import fr.unice.miage.game.gui.HealthBar;
import fr.unice.miage.game_objects.Player;
import fr.unice.miage.sprite.Sprite;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GameEngine  {

    private long lastUpdateNanoTime;
    private GameMenu gameMenu;
    private GameBoard gameBoard;
    private Repository repository;
    private CanvasGUI canvas;
    private int nbPlayers;
    private List<Player> players = new ArrayList<>();

    private Stage stage;
    private int stageWidth;
    private int stageHeight;
    private String path;

    public GameEngine(Stage stage, int width, int height, String path) {
        this.stage = stage;
        this.stageWidth = width;
        this.stageHeight = height;
        this.path = path;
        try {
            this.repository = new Repository(path);
            this.canvas = new CanvasGUI(width, height);
        } catch(Exception e){
            System.err.println(e);
        }
    }

    public void init(){
        this.gameMenu = new GameMenu(this, this.stage, this.repository);
        this.gameMenu.init();

    }

    public void start(){
        this.gameMenu.start();
    }

    public void loadingPlayers(List<List<String>> playersOptions) {
        this.nbPlayers = playersOptions.size();
        for(List<String> playerOpts : playersOptions) {
            try {
                this.players.add(new Player(playerOpts, this.repository));
            } catch (Exception e) {
                System.err.println("adding new players error " + e);
            }
        }
    }

    public void createGameBoard(){
        this.gameBoard = new GameBoard(this.stage, 600,600, this.canvas);
        this.gameBoard.init();
        this.gameBoard.start();
        this.loop();
    }

    public void loop(){
        lastUpdateNanoTime = System.nanoTime();
        new AnimationTimer(){
            //List<Player> players = this.players;

            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - lastUpdateNanoTime) / 1000000000.0;
                canvas.clean();
                for(Player player : players){
                    Sprite playerSprite = player.getPluginGraphic().getPlayerSprite();
                    HealthBar playerHealthBar = player.getPlayerHealthBar();
                    FlowPane healthBarPanel = playerHealthBar.getHealthBarPanel();
                    player.setHealth(player.getHealth()-0.01);

                    player.getPluginMovement().move(playerSprite, healthBarPanel, t, gameBoard);
                    player.getPluginGraphic().draw(canvas);
                }
                lastUpdateNanoTime = currentNanoTime;
            }
        }.start();
    }

    public static void main(String[] args) {

    }
}
