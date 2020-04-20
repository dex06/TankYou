package fr.unice.miage.utils;

public class Timer {

    double timeCumulated;
    double beginTime;
    double endTime;
    boolean running;

    public Timer(){
        //this.beginTime = System.nanoTime();
        //this.running = true;
    }

    public Timer(double time){
        this.beginTime = System.nanoTime() + time * 1000000000;
        this.running = true;
    }

    public void startChrono(){
        beginTime = System.nanoTime();
        running = true;
    }

    public void stopChrono(){
        timeCumulated += (System.nanoTime() - beginTime);
        System.out.println(timeCumulated);
        running = false;
    }

    public boolean isRunning(){ return running; }

    public double getTime(){ return System.nanoTime() / 1000000000; }

    public double getChrono(){
        return (System.nanoTime() - beginTime + timeCumulated) / 1000000000;
    }
    public void printChrono(){
        System.out.println(chronoToString());
    }
    public String chronoToString(){
        int chrono = (int) getChrono();
        int minutes = chrono / 60;
        int hours = minutes / 60;
        int seconds = chrono % 60;
        String sec = seconds < 10 ? "0" + String.valueOf(seconds) : String.valueOf(seconds);
        String min = minutes < 10 ? "0" + String.valueOf(minutes) : String.valueOf(minutes);
        String hr = hours < 10 ? "0" + String.valueOf(hours) : String.valueOf(hours);
        return hr + "." + min + ":" + sec;
    }
}
