package fr.unice.miage.engine;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.Config;
import fr.unice.miage.common.Repository;
import fr.unice.miage.common.game_objects.Obstacle;
import fr.unice.miage.common.game_objects.Player;
import fr.unice.miage.common.game_objects.Projectile;
import fr.unice.miage.common.geom.Vector2;
import fr.unice.miage.common.input.ButtonState;
import fr.unice.miage.common.input.Mouse;
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

    private final GameEngine engine;
    private final Stage stage;


    public GameEngine(Stage stage, Repository repository) {
        this.engine = this;
        this.stage = stage;
        this.repository = repository;
        this.gui1Opts = repository.getGui1PluginsNames();
        this.canvas = new CanvasGUI(Config.getWorldWidth(),Config.getWorldHeight());
        this.btnState = new ButtonState();
    }
    public Repository getRepository(){ return repository; }

    public void initMenu(){
        this.gameMenu = new GameMenu(this, stage, repository);
        gameMenu.init();
    }

    public void startMenu(){
        gameMenu.start();
    }

    public void startGame(List<String> gui1Opts, List<String> gui2Opts, List<String> configOpts, List<List<String>> playersOpts, List<String> realPlayerOpts, boolean hasRP) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

        /** Obstacle plugin **/
        if(!gui2Opts.get(0).equals("Aucun")) {
            loadingObstacle(configOpts.get(1));
            hasObstacles = true;
        }
        /** Loading players **/
        loadingPlayers(playersOpts);

        /** Collision plugin **/
        if(!configOpts.get(0).equals("Aucun")) {
            loadingCollision(configOpts.get(0));
            hasCollision = true;
        }
        /** Bar menu plugin **/
        if(!gui1Opts.get(0).equals("Aucun")) hasBarMenu = true;
        /** Statistic window plugin **/
        if(!gui2Opts.get(0).equals("Aucun")) hasStats = true;

        createGameBoard();

        /** Background plugin **/
        if(!configOpts.get(2).equals("Aucun")) {
            loadingBackground(configOpts.get(2));
            hasBackground = true;
            gameBoard.setBackground(background);
        }

        /** Real player plugin **/
        if(hasRP){
            System.out.println(realPlayerOpts.get(0));
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
        Mouse.setMouseOn();
        realPlayer.handleKeyboard(players.get(0), canvas, btnState);
        realPlayer.handleMouse(players.get(0), canvas, btnState);
        hasRealPlayer = true;
        //gameBoard.setCanvas(canvas);

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
    public void resetListOfPlayers() {
        players = new ArrayList<>();
    }

    public void createGameBoard(){
        gameBoard = new GameBoard(stage, 600,600, canvas, repository);
        gameBoard.init(hasBarMenu);
        if(hasObstacles) obstaclesList = obstacles.generate();
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
            createRandPosition(player);
        }
    }
    public void createRandPosition(Player player){
        double lesser = Math.min(Config.getWorldWidth(), Config.getWorldHeight());
        double min = player.getSprite().getWidth();
        Vector2 randVector = Randomizer.getRandomVector(min, lesser/1.5);
        if(!outOfObstacles(player, randVector)) createRandPosition(player);
        else {
            player.addPosition(randVector);
            player.addVelocity(Randomizer.getRandomVector(-0.3, 0.3));
        }
    }

    public boolean outOfObstacles(Player player, Vector2 v){

        for(Obstacle obs : obstaclesList){
            double xMin = obs.getPosition().getX();
            double yMin = obs.getPosition().getY();
            double xMax = xMin + obs.getSprite().getWidth();
            double yMax = yMin + obs.getSprite().getHeight();
            double pxMin = v.getX();
            double pyMin = v.getY();
            double pxMax = pxMin + player.getSprite().getWidth();
            double pyMax = pyMin + player.getSprite().getHeight();
            if(pxMin >= xMin & pyMin >= yMin & pxMin <= xMax & pyMin <= yMax) return false;
            if(pxMax >= xMin & pyMax >= yMin & pxMax <= xMax & pyMax <= yMax) return false;
            if(pxMax >= xMin & pyMin >= yMin & pxMax <= xMax & pyMin <= yMax) return false;
            if(pxMin >= xMin & pyMax >= yMin & pxMin <= xMax & pyMax <= yMax) return false;
        }
        return true;
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

    protected void removeDeadProjectiles(){
        for(Player player : players){
            List<Projectile> prjList = new ArrayList<>();
            for(Projectile prj : player.getProjectiles()){
                if(!prj.hasEnded()) prjList.add(prj);
            }
            player.setProjectiles(prjList);
        }
    }
    protected  void resetPlayersStats(){
        for(Player player : players){
            player.resetStat();
        }
    }

    public void loop(){
        Config.setPlay();
        btnState.reset();
        lastUpdateNanoTime = System.nanoTime();
        new AnimationTimer(){
            public void handle(long currentNanoTime) {
                if (Config.getGameState() == Config.getPlayState()) {
                    if (!Timer.isRunning()) Timer.startChrono();
                    double t = Timer.getTime();

                    canvas.clean();

                    /** Obstacles **/
                    if (hasObstacles) {
                        for (Obstacle obs : obstaclesList) {
                            obs.draw(canvas);
                        }
                    }
                    for (Player player : players) {
                        /** Human player **/
                        if(hasRealPlayer & players.indexOf(player) == 0) {
                            if(player.isAlive()) {
                                realPlayer.handleKeyInput(player, btnState);
                                Vector2 difVector = Mouse.getDifVector();
                                Vector2 lastAxis = Mouse.getLastMousePosition().sub2(player.getCenterPosition()).norm2();
                                Vector2 previousAxis = lastAxis.sub2(difVector).norm2();
                                double rot =  Math.toDegrees(Math.acos(previousAxis.dot(lastAxis)));
                                player.setWeaponRotation(rot);
                            }
                        } else if (player.isAlive()) {
                            if(player.hasMove()) player.move();
                        }
                        if(hasBarMenu) gameBoard.setTimer();
                        /**  Weapon part **/
                        if(player.hasWeapon()) {
                            if(player.isAlive()) {
                                if (hasRealPlayer) {
                                    if(players.indexOf(player) != 0) player.shoot();
                                }
                                else player.shoot();
                            }
                            player.drawProjectiles();
                            player.moveProjectiles();
                        }
                        /** Player draw sprite **/
                        if(player.hasGraphic()) player.draw();
                        // If we have a winner => end of game +- stats
                        if (numberOfPlayersAlive() <= 1) {
                            if(Timer.isRunning()) {
                                Timer.stopChrono();
                                if (getWinningPlayerNumber() != -1) {
                                    hasWinner = true;
                                    createGameStats();
                                    Player winningPlayer = players.get(getWinningPlayerNumber());
                                    gameStats.setWinner(winningPlayer);
                                } else createGameStats();
                                gameBoard.stop();
                                this.stop();
                                if(hasStats) {
                                    gameStats.start();
                                    gameStats.setStats(players);
                                    gameStats.setRestartBtn(engine);
                                }

                            }
                        }
                    }
                    // Checking for all the types of collisions
                    if(hasCollision) collision.checkAllCollisions(players, obstaclesList);
                    removeDeadProjectiles();
                    lastUpdateNanoTime = currentNanoTime;
                }
                // If we pause the game
                if(Config.getGameState() == Config.getPauseState()){
                    if(Timer.isRunning()) Timer.stopChrono();
                }
                // If we stop the game
                if(Config.getGameState() == Config.getStopState()) {
                    if(Timer.isRunning()) {
                        Timer.stopChrono();
                        gameBoard.stop();
                        this.stop();
                        createGameStats();
                        if(hasStats) {
                            gameStats.start();
                            gameStats.setStats(players);
                            gameStats.setRestartBtn(engine);
                        }
                    }
                    this.stop();
                    gameBoard.stop();
                    createGameStats();
                    if(hasStats) {
                        gameStats.start();
                        gameStats.setStats(players);
                        gameStats.setRestartBtn(engine);
                    }
                //if we restart the game
                } else if(Config.getGameState() == Config.getRestartState()){
                    if(Timer.isRunning()) {
                        Timer.stopChrono();
                        gameBoard.stop();
                        this.stop();
                        resetPlayersStats();
                        Mouse.setMouseOff();
                        initMenu();
                        startMenu();
                    }
                }
            }
        }.start();
    }
}
