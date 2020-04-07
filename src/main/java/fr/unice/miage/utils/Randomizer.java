package fr.unice.miage.utils;

public class Randomizer {

    public static double getRandomDoubleInRange(int min, int max){
        return Math.random() * (max - min) + min;
    }
}
