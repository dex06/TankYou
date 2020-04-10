package fr.unice.miage.game;

import fr.unice.miage.game.gui.CanvasGUI;
import fr.unice.miage.game.gui.GameBoard;
import fr.unice.miage.game.gui.GameMenu;
import fr.unice.miage.game.gui.HealthBar;
import fr.unice.miage.game_objects.Player;
import fr.unice.miage.plugins.PlugInCollision;
import fr.unice.miage.sprite.Sprite;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class GameEngine  {

    private long lastUpdateNanoTime;
    private GameMenu gameMenu;
    private GameBoard gameBoard;
    private Repository repository;
    private CanvasGUI canvas;
    private PlugInCollision collision;
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

    public void initMenu(){
        this.gameMenu = new GameMenu(this, this.stage, this.repository);
        this.gameMenu.init();

    }

    public void startMenu(){
        this.gameMenu.start();
    }

    public void startGame(List<String> guiOpts, List<String> configOpts, List<List<String>> playersOpts) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        this.loadingPlayers(playersOpts);
        this.loadingCollision(configOpts.get(0));
        this.createGameBoard();
    }

    public void loadingCollision(String opt) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.collision = this.repository.loadCollision(opt);
    }

    public void loadingPlayers(List<List<String>> playersOptions) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.nbPlayers = playersOptions.size();
        for(List<String> playerOpts : playersOptions) {
            this.players.add(new Player(playerOpts, this.repository, this.canvas));
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
                    player.getPlayerHealthBar().draw();
                    HealthBar playerHealthBar = player.getPlayerHealthBar();
                    FlowPane healthBarPanel = playerHealthBar.getHealthBarPanel();
                    player.setHealth(player.getHealth()-0.01);

                    player.getPluginMovement().move(player, playerSprite, healthBarPanel, t, gameBoard);
                    player.getPluginGraphic().draw(canvas);
                }
                collision.checkAllCollisions(players);
                lastUpdateNanoTime = currentNanoTime;
            }
        }.start();
    }

    public static void main(String[] args) {

    }
}
