package fr.unice.miage.common.input;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.geom.Vector2;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

public class Mouse {

    private static boolean mouseOn = false;
    private static Vector2 lastShootingPosition = new Vector2();
    private static Vector2 lastMousePosition = new Vector2();
    private static Vector2 difVector = new Vector2();

    public static void handleMouseEvent(CanvasGUI canvas, ButtonState btnState){
        Canvas c = canvas.getCanvas();
        c.setFocusTraversable(true);
        c.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btnState.shot = true;
                double mouseX = mouseEvent.getSceneX();
                double mouseY = mouseEvent.getSceneY();
                lastShootingPosition = new Vector2(mouseX, mouseY);
            }
        });
        c.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btnState.shot = false;
            }
        });

        c.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                c.setCursor(Cursor.CROSSHAIR);
            }
        });

        c.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                double mouseX = mouseEvent.getSceneX();
                double mouseY = mouseEvent.getSceneY();
                difVector = lastMousePosition.sub2(new Vector2(mouseX, mouseY));
                lastMousePosition = new Vector2(mouseX, mouseY);

            }
        });
    }

    public static Vector2 getLastShootingPosition(){ return lastShootingPosition; }
    public static Vector2 getLastMousePosition(){ return lastMousePosition; }
    public static Vector2 getDifVector(){ return difVector; }

    public static boolean isMouseOn(){ return mouseOn; }
    public static void setMouseOn(){ mouseOn = true; }
    public static void setMouseOff() { mouseOn = false; }
}
