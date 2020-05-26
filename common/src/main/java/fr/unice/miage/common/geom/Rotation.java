package fr.unice.miage.common.geom;

public class Rotation {

    public static double rotation2Vectors(Vector2 vA, Vector2 vB){
        return Math.toDegrees(Math.atan2(vB.getY(),vB.getX()) - Math.atan2(vA.getY(), vA.getX()));
    }

    public static Vector2 getTransVecAfterRot(Vector2 v, double rot) {
        double x = v.getX();
        double y = v.getY();
        double rad = Math.toRadians(rot);
        double rotX = -y * Math.sin(rad) + x * Math.cos(rad);
        double rotY = y * Math.cos(rad) + x * Math.sin(rad);
        return new Vector2(rotX-x, -rotY-y);
    }

    public static Vector2 getTransformVector(Vector2 v, double rot){
        double x = v.getX();
        double y = v.getY();
        double rad = Math.toRadians(rot);
        double rotX = -y * Math.sin(rad) + x * Math.cos(rad);
        double rotY = y * Math.cos(rad) + x * Math.sin(rad);
        return new Vector2(rotX, -rotY);
    }
}
