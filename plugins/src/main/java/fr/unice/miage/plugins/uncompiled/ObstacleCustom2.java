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

public class ObstacleCustom2 implements PlugInObstacle {

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

    private List<Obstacle> generateTShape() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<Obstacle> listReturnT = new ArrayList<>();
        Obstacle barre = new Obstacle(new Vector2(generateRandom(10, 300), generateRandom(10, 300)), new Vector2(100, 40));
        Obstacle barre2 = new Obstacle(new Vector2(barre.getPosition().getX() + barre.getSize().getX()/3,
                barre.getPosition().getY() + barre.getSize().getY()),
                new Vector2(barre.getSize().getX()/3,
                        60));

        listReturnT.add(barre);
        listReturnT.add(barre2);

        return listReturnT;
    }
    private List<Obstacle> generateLShape(){
        return null;
    }
    private List<Obstacle> generateUShape(){
        return null;
    }
    public List<Obstacle> generate() {
        List<Obstacle> listReturn = new ArrayList<>();

        try {
            for (int i = 0; i < 10 ; i++){
                int forme = (int)(Math.random() * (2));
                // 1 - T, 2 - U, 3 - L
                switch (forme){
                    case 1:
                        listReturn.addAll(generateTShape());
                        break;
//                    case 2:
//                        listReturn.addAll(generateUShape());
//                        break;
//                    case 3:
//                        listReturn.addAll(generateLShape());
//                        break;
                    default:
                        listReturn.add(new Obstacle(new Vector2(10 + (int)(Math.random() * ((600 - 10) + 1)), 10 + (int)(Math.random() * ((600 - 10) + 1))), new Vector2(10 + (int)(Math.random() * ((70 - 10) + 1)), 10 + (int)(Math.random() * ((70 - 10) + 1)))));
                }

//                int nombreAleatoire = 10 + (int)(Math.random() * ((70 - 10) + 1));
                listReturn.add(new Obstacle(new Vector2(10 + (int)(Math.random() * ((600 - 10) + 1)), 10 + (int)(Math.random() * ((600 - 10) + 1))), new Vector2(10 + (int)(Math.random() * ((70 - 10) + 1)), 10 + (int)(Math.random() * ((70 - 10) + 1)))));
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
