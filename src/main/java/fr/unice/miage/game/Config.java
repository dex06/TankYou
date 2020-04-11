package fr.unice.miage.game;

public class Config {

    private static double worldWidth = 600;
    private static double worldHeight = 600;

    public static void setWorldWidth(double width){ worldWidth = width; }
    public static void setWorldHeight(double height){ worldHeight = height; }
    public static double getWorldWidth(){ return worldWidth; }
    public static double getWorldHeight(){ return worldHeight; }

}
