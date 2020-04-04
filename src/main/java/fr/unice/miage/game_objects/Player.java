package fr.unice.miage.game_objects;

import fr.unice.miage.geom.Vector2;

public class Player {
    Vector2 position;
    Vector2 velocity;
    Vector2 acceleration;

    public Player(){
        this.position = new Vector2();
        this.velocity = new Vector2();
        this.acceleration = new Vector2();
    }
}
