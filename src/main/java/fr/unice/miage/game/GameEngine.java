package fr.unice.miage.game;

import fr.unice.miage.game.gui.CanvasGUI;
import fr.unice.miage.game.gui.GameBoard;
import fr.unice.miage.game.gui.GameMenu;
import fr.unice.miage.game_objects.Player;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;

import java.io.File;
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
    private File path;

    public GameEngine(Stage stage, int width, int height, File path) {
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
        this.gameMenu = new GameMenu(this, this.stage);
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
    }

    public void loop(){

        //Repository repo = new Repository(new File());
        /* ClassLoader loader = new URLClassLoader( new URL[] { new File("/Users/haddad/.m2/repository/com/company/somejar-1.0.0-SNAPSHOT.jar").toURL() });
        Class<moveClass> c1 = (Class<moveClass>)loader.loadClass("chemin");
        Constructor<PlugInMovement> constructor = c1.getConstructor(String.class);
        PlugInMovement movePlugIn = constructor.newInstance("myString"); */

        lastUpdateNanoTime = System.nanoTime();
        new AnimationTimer(){
            //List<Player> players = this.players;

            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - lastUpdateNanoTime) / 1000000000.0;
                for(Player player : players){
                    //player.getPluginMovement().move();
                }
                lastUpdateNanoTime = currentNanoTime;
            }

        }.start();
        this.stage.show();
    }

    public static void main(String[] args) {

    }
}
