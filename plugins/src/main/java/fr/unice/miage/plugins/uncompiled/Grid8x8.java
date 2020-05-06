package fr.unice.miage.plugins.uncompiled;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.game_objects.Obstacle;
import fr.unice.miage.common.geom.Vector2;
import fr.unice.miage.common.plugins.PlugInObstacle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Grid8x8 implements PlugInObstacle {

    public void draw(CanvasGUI canvas, Obstacle obstacle) {
        GraphicsContext gc = canvas.getGraphicsContext();
//        gc.setStroke(Color.BLACK);
//        gc.strokeRect(obstacle.getPosition().getX(), obstacle.getPosition().getY(), obstacle.getSize().getX(), obstacle.getSize().getY());
        gc.setFill(Color.BLACK);
        gc.fillRect(obstacle.getPosition().getX(), obstacle.getPosition().getY(), obstacle.getSize().getX(), obstacle.getSize().getY());

    }

    private int generateRandom(int min, int max){
        return (int)(min + Math.random() * ((max - min) + 1));
    }


    public List<Obstacle> generate() {
        List<Obstacle> listReturn = new ArrayList<>();

        try {
            for (int i = 0; i < 64 ; i++){
//                int nombreAleatoire = 10 + (int)(Math.random() * ((70 - 10) + 1));
                if((int)(Math.random() * 10) <= 2){
                    listReturn.add(new Obstacle(new Vector2(600/8*(i%8), 600/8*(i/8)), new Vector2(600/8, 600/8)));
                }
//                listReturn.add(new Obstacle(new Vector2(100, 100), new Vector2(50, 50)));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return listReturn;
    }
}
