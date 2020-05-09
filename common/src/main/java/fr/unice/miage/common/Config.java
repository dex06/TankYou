package fr.unice.miage.common;

public class Config {

    private static double worldWidth = 600;
    private static double worldHeight = 600;
    private static boolean testing = false;


    private enum States {PLAY, PAUSE, STOP, RESTART}

    private static States gameState = States.PLAY;

    public static void setWorldWidth(double width){ worldWidth = width; }
    public static void setWorldHeight(double height){ worldHeight = height; }
    public static double getWorldWidth(){ return worldWidth; }
    public static double getWorldHeight(){ return worldHeight; }

    public static void setTesting(boolean bool){ testing = bool; }
    public static boolean getTesting(){ return testing; }

    public static States getGameState(){ return gameState; }
    public static void setPlay(){ gameState = States.PLAY; }
    public static void setPause(){ gameState = States.PAUSE; }
    public static void setStop(){ gameState = States.STOP; }
    public static void setRestart() { gameState = States.RESTART; }
    public static States getPlayState(){ return States.PLAY; }
    public static States getPauseState(){ return States.PAUSE; }
    public static States getStopState(){ return States.STOP; }
    public static States getRestartState() { return States.RESTART; }
}
