package fr.unice.miage.game;

import fr.unice.miage.game.gui.CanvasGUI;
import fr.unice.miage.game.gui.GameBoard;
import fr.unice.miage.game.gui.GameMenu;
import fr.unice.miage.game.gui.GameStats;
import fr.unice.miage.game_objects.Player;
import fr.unice.miage.plugins.PlugInCollision;
import fr.unice.miage.utils.Finder;
import fr.unice.miage.utils.Randomizer;
import fr.unice.miage.utils.Timer;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class GameEngine  {

    private long lastUpdateNanoTime;
    private GameMenu gameMenu;
    private GameBoard gameBoard;
    private GameStats gameStats;
    private Repository repository;
    private CanvasGUI canvas;
    private PlugInCollision collision;
    private int nbPlayers;
    private List<Player> players = new ArrayList<>();
    private List<String> gui1Opts = new ArrayList<>();


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
            this.gui1Opts = repository.getGui1PluginsNames();
            this.canvas = new CanvasGUI(width, height);
        } catch(Exception e){
            System.err.println(e);
        }
    }

    public void initMenu(){
        this.gameMenu = new GameMenu(this, stage, repository);
        gameMenu.init();
    }

    public void startMenu(){
        gameMenu.start();
    }

    public void startGame(List<String> gui1Opts, List<String> gui2Opts, List<String> configOpts, List<List<String>> playersOpts) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        //loadingGUI1();
        loadingPlayers(playersOpts);
        loadingCollision(configOpts.get(0));
        createGameBoard();
    }

    public void loadingCollision(String opt) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        collision = repository.loadCollision(opt);
    }

    public void loadingPlayers(List<List<String>> playersOptions) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        nbPlayers = playersOptions.size();
        int playerID = 0;
        for(List<String> playerOpts : playersOptions) {
            playerID++;
            players.add(new Player(playerOpts, repository, canvas, playerID));
        }
        Finder.setPlayers(players);
    }

    public void createGameBoard(){
        gameBoard = new GameBoard(stage, 600,600, canvas);
        gameBoard.init();
        gameBoard.start();
        giveRandomPositionAndVelocityToPlayers();
        loop();
    }

    public void giveRandomPositionAndVelocityToPlayers(){
        for(Player player : players){
            player.addPosition(Randomizer.getRandomVector(10, 400));
            player.addVelocity(Randomizer.getRandomVector(-0.3, 0.3));
        }
    }

    protected int numberOfPlayersAlive(){
        int nb = 0;
        for(Player player : players) if(player.isAlive()) nb++;
        return nb;
    }

    protected int getWinningPlayerNumber(){
        int winningPlayerNumber = -1;
        for(int i = 0; i < players.size()-1; i++){
            if(players.get(i).isAlive()) {
                winningPlayerNumber = i;
                break;
            }
        }
        return winningPlayerNumber;
    }

    public void loop(){
        Timer timer = new Timer();
        lastUpdateNanoTime = System.nanoTime();
        new AnimationTimer(){
            public void handle(long currentNanoTime) {
                if (Config.getGameState() == Config.getPlayState()) {
                    timer.startChrono();
                    double t = timer.getTime();
//                System.out.println(t);
                    canvas.clean();
                    for (Player player : players) {
                        if (player.isAlive()) {
                            player.move();
                        }
                        ;
                        player.draw();
                        player.checkProjectileOut();
                        for (int counter = 0; counter < player.projectiles.size(); counter++) {
//                            player.projectiles.get(counter).move();
                            player.moveProjectile(player.projectiles.get(counter));
                            player.projectiles.get(counter).draw(canvas);
                            if (player.projectiles.get(counter).checkCollisionsWithPlayer(players)) {
                                player.projectiles.remove(counter);
                            }
                        }
                        if ((currentNanoTime / 1000000000) - player.lastShot > 1) {
                            System.out.println(player.getName() + " shot ");
                            player.lastShot = currentNanoTime / 1000000000;
                            System.out.println(player.getName() + " près de " + Finder.findClosestPlayer(player).getName());
                            player.shot();
//                        System.out.println("Size Projectile " + player.projectiles.size());
                        }
                        if (numberOfPlayersAlive() <= 1) gameBoard.stop();
                    }
                    collision.checkAllCollisions(players);
                    lastUpdateNanoTime = currentNanoTime;
                } if(Config.getGameState() == Config.getPauseState()) timer.stopChrono();
                else if(Config.getGameState() == Config.getStopState()) {
                    timer.stopChrono();
                    gameBoard.stop();
                }
            }
        }.start();
    }
}
