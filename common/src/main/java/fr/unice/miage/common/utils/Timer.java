package fr.unice.miage.common.utils;

public class Timer {

    private static double timeCumulated;
    private static double beginTime;
    private static double endTime;
    private static boolean running;


    public static void startChrono(){
        beginTime = System.nanoTime();
        running = true;
    }

    public static void stopChrono(){
        timeCumulated += (System.nanoTime() - beginTime);
        running = false;
    }

    public static boolean isRunning(){ return running; }

    public static double getTime(){ return System.nanoTime() / 1000000000; }

    public static double getChrono(){
        return (System.nanoTime() - beginTime + timeCumulated) / 1000000000;
    }
    public void printChrono(){
        System.out.println(chronoToString());
    }
    public static String chronoToString(){
        int chrono = (int) getChrono();
        int minutes = chrono / 60;
        int hours = minutes / 60;
        int seconds = chrono % 60;
        String sec = seconds < 10 ? "0" + seconds : String.valueOf(seconds);
        String min = minutes < 10 ? "0" + minutes : String.valueOf(minutes);
        String hr = hours < 10 ? "0" + hours : String.valueOf(hours);
        return hr + "." + min + ":" + sec;
    }
}
