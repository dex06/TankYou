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

public class ObstacleCustom implements PlugInObstacle {
    @Override
    public void draw(CanvasGUI canvas, Obstacle obstacle) {
        GraphicsContext gc = canvas.getGraphicsContext();
        gc.setFill(Color.BLACK);
        gc.fillRect(obstacle.getPosition().getX(), obstacle.getPosition().getY(), obstacle.getSize().getX(), obstacle.getSize().getY());
    }

    @Override
    public List<Obstacle> generate() {
        List<Obstacle> listReturn = new ArrayList<>();
        try {
            listReturn.add(new Obstacle(new Vector2(50, 50), new Vector2(50, 50)));
            listReturn.add(new Obstacle(new Vector2(100, 100), new Vector2(50, 50)));
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
