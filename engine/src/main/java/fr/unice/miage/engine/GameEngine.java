package fr.unice.miage.engine;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.Config;
import fr.unice.miage.common.Repository;
import fr.unice.miage.common.game_objects.Obstacle;
import fr.unice.miage.common.game_objects.Player;
import fr.unice.miage.common.input.ButtonState;
import fr.unice.miage.common.plugins.PlugInBackground;
import fr.unice.miage.common.plugins.PlugInCollision;
import fr.unice.miage.common.plugins.PlugInObstacle;
import fr.unice.miage.common.plugins.PlugInRealPlayer;
import fr.unice.miage.common.utils.Finder;
import fr.unice.miage.common.utils.Randomizer;
import fr.unice.miage.common.utils.Timer;
import fr.unice.miage.engine.gui.GameBoard;
import fr.unice.miage.engine.gui.GameMenu;
import fr.unice.miage.engine.gui.GameStats;
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
    private ButtonState btnState;
    private PlugInCollision collision;
    private PlugInObstacle obstacles;
    private PlugInBackground background;
    private PlugInRealPlayer realPlayer;
    private int nbPlayers;
    private List<Player> players = new ArrayList<>();
    private List<String> gui1Opts = new ArrayList<>();
    private List<Obstacle> obstaclesList = new ArrayList<>();
    private boolean hasBarMenu = false;
    private boolean hasCollision = false;
    private boolean hasObstacles = false;
    private boolean hasBackground = false;
    private boolean hasStats = false;
    private boolean hasWinner = false;
    private boolean hasRealPlayer = false;


    private Stage stage;
    private double stageWidth;
    private double stageHeight;
    private String path;

    public GameEngine(Stage stage, double width, double height, String path) {
        this.stage = stage;
        this.stageWidth = width;
        this.stageHeight = height;
        this.path = path;
        this.btnState = new ButtonState();
        try {
            this.repository = new Repository(path);
            this.gui1Opts = repository.getGui1PluginsNames();
            this.canvas = new CanvasGUI(width, height);
        } catch(Exception e){
            System.err.println("GameEngine Initialisation error " + e);
        }
    }

    public void initMenu(){
        this.gameMenu = new GameMenu(this, stage, repository);
        gameMenu.init();
    }

    public void startMenu(){
        gameMenu.start();
    }

    public void startGame(List<String> gui1Opts, List<String> gui2Opts, List<String> configOpts, List<List<String>> playersOpts, List<String> realPlayerOpts) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        //loadingGUI1();
        loadingPlayers(playersOpts);
        if(!configOpts.get(0).equals("Aucun")) {
            loadingCollision(configOpts.get(0));
            hasCollision = true;
        }
        if(!configOpts.get(1).equals("Aucun")) {
            loadingObstacle(configOpts.get(1));
            hasObstacles = true;
        }
        if(!configOpts.get(2).equals("Aucun")) {
            loadingBackground(configOpts.get(2));
            hasBackground = true;
        }
        if(!gui1Opts.get(0).equals("Aucun")) hasBarMenu = true;
        if(!gui2Opts.get(0).equals("Aucun")) hasStats = true;

        createGameBoard();

        if(!realPlayerOpts.isEmpty()){
            loadingRealPlayer(realPlayerOpts.get(0));
            hasRealPlayer = true;
        }

    }

    public void loadingCollision(String opt) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        collision = repository.loadCollision(opt);
        hasCollision = true;
    }

    public void loadingObstacle(String opt) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        obstacles = repository.loadObstacle(opt);
       hasObstacles = true;
    }

    public void loadingBackground(String opt) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        background = repository.loadBackground(opt);
        hasBackground = true;
    }

    public void loadingRealPlayer(String opt) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        realPlayer = repository.loadRealPlayer(opt);

        realPlayer.handleKeyboard(players.get(0), canvas, btnState);
        realPlayer.handleMouse(players.get(0), canvas, btnState);
        hasRealPlayer = true;
        //gameBoard.setCanvas(canvas);
        System.out.println("Real player created");
    }


    public void loadingPlayers(List<List<String>> playersOptions) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        nbPlayers = playersOptions.size();
        int playerID = 0;
        for(List<String> playerOpts : playersOptions) {
            playerID++;
            players.add(new Player(playerOpts, repository, canvas, playerID));
        }
        Finder.setPlayers(players);
    }

    public void createGameBoard(){
        gameBoard = new GameBoard(stage, 600,600, canvas, repository);
        gameBoard.init(hasBarMenu);
        if(hasObstacles)
            obstaclesList = obstacles.generate();
        gameBoard.start();
        giveRandomPositionAndVelocityToPlayers();
        loop();
    }

    public void createGameStats(){
        gameStats = new GameStats(stage, 600, 600);
        gameStats.init(hasWinner, hasStats);
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
        Config.setPlay();
        btnState.reset();
        Timer timer = new Timer();
        lastUpdateNanoTime = System.nanoTime();
        new AnimationTimer(){
            public void handle(long currentNanoTime) {
                if (Config.getGameState() == Config.getPlayState()) {
                    if(!timer.isRunning()) timer.startChrono();
                    double t = timer.getTime();
//                System.out.println(t);
                    canvas.clean();
                    if(hasBackground) background.draw(canvas);
                    for (Obstacle obs : obstaclesList){
                        obstacles.draw(canvas, obs);
                    }
                    for (Player player : players) {
                        if(hasRealPlayer & players.indexOf(player) == 0) {
                            realPlayer.handleKeyInput(players.get(0), btnState);
                           //player.draw();
                          //btnState.reset();
                        } else if (player.isAlive()) {
                            if(player.hasMove()) player.move();
                        }
                        if(player.hasGraphic()) player.draw();

                        if(hasBarMenu) gameBoard.setTimer(timer);

                        if(player.hasWeapon()) {
//                            player.checkProjectileOut();
//                            for (int counter = 0; counter < player.projectiles.size(); counter++) {
//                                player.projectiles.get(counter).move();
//                                player.moveProjectile(player.projectiles.get(counter));
//                                player.projectiles.get(counter).draw(canvas);
//                                if (player.projectiles.get(counter).checkCollisionsWithPlayer(players)) {
//                                    player.projectiles.remove(counter);
//                                }
//                            }
                            player.drawProjectiles();
                            player.getPluginWeapon().shot(player, currentNanoTime);
                            player.moveProjectiles();

                        }
                        // If we have a winner => end of game +- stats
                        if (numberOfPlayersAlive() <= 1) {
                            if(timer.isRunning()) {
                                timer.stopChrono();
                                if (getWinningPlayerNumber() != -1) {
                                    hasWinner = true;
                                    createGameStats();
                                    Player winningPlayer = players.get(getWinningPlayerNumber());
                                    gameStats.setWinner(winningPlayer);
                                } else createGameStats();
                                gameBoard.stop();
                                this.stop();
                                if(hasStats) gameStats.start();

                            }
                        }
                    }
                    // Checking for all the types of collisions
                    if(hasCollision) collision.checkAllCollisions(players);
                    lastUpdateNanoTime = currentNanoTime;
                }
                // If we pause the game
                if(Config.getGameState() == Config.getPauseState()) if(timer.isRunning()) timer.stopChrono();
                // If we stop the game
                if(Config.getGameState() == Config.getStopState()) {
                    if(timer.isRunning()) {
                        timer.stopChrono();
                        gameBoard.stop();
                        this.stop();
                        createGameStats();
                        if(hasStats) gameStats.start();
                    }
                    this.stop();
                    gameBoard.stop();
                    createGameStats();
                    if(hasStats) gameStats.start();
                //if we restart the game
                } else if(Config.getGameState() == Config.getRestartState()){
                    if(timer.isRunning()) {
                        timer.stopChrono();
                        gameBoard.stop();
                        this.stop();
                        initMenu();
                        startMenu();
                    }
                }
            }
        }.start();
    }
}
