package fr.unice.miage.common.geom;

public class Rotation {

    public static double rotation2Vectors(Vector2 vA, Vector2 vB){
        //return Math.toDegrees(1 - v1.dot(v2));
        return Math.toDegrees(Math.atan2(vB.getY(),vB.getX()) - Math.atan2(vA.getY(), vA.getX()));
        //return (Math.atan2(position.getY(), position.getX()) / (2 * Math.PI));
        //return Math.toDegrees(vA.dot(vB) / vA.norm2().dot(vB.norm2()));
        //return Math.toDegrees(Math.acos(vA.norm2().dot(vB.norm2())));
    }
    public static Vector2 getTransVecAfterRot(Vector2 v, double rot) {
        double x = v.getX();
        double y = v.getY();
        double rad = Math.toRadians(rot);
        double rotX = -y * Math.sin(rad) + x * Math.cos(rad);
        System.out.println("rotx : " + rotX);
        double rotY = y * Math.cos(rad) + x * Math.sin(rad);
        System.out.println("rotY : " + rotY);
        return new Vector2(rotX-x, -rotY-y);
    }

    public static void main(String[] args) {
        Vector2 v1 = new Vector2(-2, -4);
        Vector2 v2 = new Vector2(0, -1);
        Vector2 v3 = new Vector2(2,0);
        System.out.println(Rotation.rotation2Vectors(v2, v1));
        Vector2 rotVec = Rotation.getTransVecAfterRot(v3, 90);
        System.out.println("x : " + rotVec.getX() + ", y : " + rotVec.getY() );
    }
}
