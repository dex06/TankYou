package fr.unice.miage.common.geom;

public class Rotation {

    public static double rotation2Vectors(Vector2 vA, Vector2 vB){
        //return Math.toDegrees(1 - v1.dot(v2));
        return Math.toDegrees(Math.atan2(vB.getY(),vB.getX()) - Math.atan2(vA.getY(), vA.getX()));
        //return (Math.atan2(position.getY(), position.getX()) / (2 * Math.PI));
        //return Math.toDegrees(vA.dot(vB) / vA.norm2().dot(vB.norm2()));
        //return Math.toDegrees(Math.acos(vA.norm2().dot(vB.norm2())));
    }
    public static void main(String[] args) {
        Vector2 v1 = new Vector2(-2,-4);
        Vector2 v2 = new Vector2(0,-1);
        System.out.println(Rotation.rotation2Vectors(v2,v1));
    }
}
