package fr.unice.miage.game;

import fr.unice.miage.game.gui.CanvasGUI;
import fr.unice.miage.game.gui.GameBoard;
import fr.unice.miage.game.gui.GameMenu;
import fr.unice.miage.game_objects.Player;
import fr.unice.miage.plugins.PlugInCollision;
import fr.unice.miage.utils.Randomizer;
import javafx.animation.AnimationTimer;
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
    private double stageWidth;
    private double stageHeight;
    private String path;

    public GameEngine(Stage stage, double width, double height, String path) {
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
        this.giveRandomPositionsAndVelocityToPlayers();
        this.loop();
    }

    public void giveRandomPositionsAndVelocityToPlayers(){
        for(Player player : players){
            player.addPosition(Randomizer.getRandomVector(10, 400));
            player.addVelocity(Randomizer.getRandomVector(-0.3, 0.3));
        }
    }

    public void loop(){
        lastUpdateNanoTime = System.nanoTime();
        new AnimationTimer(){
            //List<Player> players = this.players;

            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - lastUpdateNanoTime) / 1000000000.0;
                canvas.clean();
                for(Player player : players){

                    player.getPluginMovement().move(player);
                    player.getPluginGraphic().draw(canvas);
                    player.setHealth(player.getHealth()-0.01);

                }
                if(players.size() > 1) collision.checkAllCollisions(players);
                lastUpdateNanoTime = currentNanoTime;
            }
        }.start();
    }

    public static void main(String[] args) {

    }
}
