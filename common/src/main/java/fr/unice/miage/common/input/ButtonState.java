package fr.unice.miage.common.input;

public class ButtonState {
    public boolean up = false;
    public boolean down = false;
    public boolean left = false;
    public boolean right = false;
    public boolean shot = false;

    public void reset(){
        up = false;
        down = false;
        left = false;
        right = false;
        shot = false;
    }
}
