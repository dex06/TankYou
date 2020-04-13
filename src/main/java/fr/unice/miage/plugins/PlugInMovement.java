package fr.unice.miage.plugins;

import fr.unice.miage.game_objects.Player;
import fr.unice.miage.geom.Vector2;

public interface PlugInMovement {

    void move(Player player);

    double getSpeedX();
    double getSpeedY();
    void setSpeedX(double x);
    void setSpeedY(double y);
    void reverseSpeed();

    Vector2 getPosition();
    void setPosition(Vector2 v);
    void addPosition(Vector2 v);

    Vector2 getVelocity();
    void setVelocity(Vector2 v);
    void addVelocity(Vector2 v);

    Vector2 getAcceleration();
    void setAcceleration(Vector2 v);
    void addAcceleration(Vector2 v);
}
