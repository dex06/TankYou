package fr.unice.miage.utils;

import fr.unice.miage.geom.Vector2;

public class Randomizer {

    public static int getRandomIntInRange(int min, int max) { return (int) (Math.random() * (max - min) + min); }

    public static double getRandomDoubleInRange(double min, double max){
        return Math.random() * (max - min) + min;
    }

    public static Vector2 getRandomVector(double min, double max){
        double x = Randomizer.getRandomDoubleInRange(min, max);
        double y = Randomizer.getRandomDoubleInRange(min, max);
        return new Vector2(x, y);
    }

}
