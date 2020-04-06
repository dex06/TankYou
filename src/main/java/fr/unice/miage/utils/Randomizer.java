package fr.unice.miage.utils;

public class Randomizer {

    public static double getRandomDoubleInRange(int min, int max){
        double res = Math.random() * (max - min) + min;
        System.out.println(res);
        return res;
    }
}
