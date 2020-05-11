package fr.unice.miage.common.geom;

public class Rotation {

    public static double rotation2Vectors(Vector2 vA, Vector2 vB){
        //return Math.toDegrees(1 - v1.dot(v2));
        //return Math.toDegrees(Math.atan2(v2.getY(),v2.getX()) - Math.atan2(v1.getY(), v1.getX()));
        //return (Math.atan2(position.getY(), position.getX()) / (2 * Math.PI));
        return Math.toDegrees(Math.acos(vA.norm2().dot(vB.norm2())));
    }
}
