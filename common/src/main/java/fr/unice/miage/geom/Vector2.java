package fr.unice.miage.geom;

public class Vector2 {
    private double x;
    private double y;

    public Vector2(){
        this.x = 0;
        this.y = 0;
    }
    public Vector2(double x, double y){
        this.x = x;
        this.y = y;
    }

    //TODO a enlever
    public void setX(double x ){ this.x = x; }
    public void setY(double y ){ this.y = y; }


    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }

    public Vector2 copy() {
        return new Vector2(x, y);
    }

    public void limit(double limit){
        if (length() > limit){
            double scale = limit / length();
            mult(scale);
        }
    }

    public Vector2 limit2(double limit){
        if (length() > limit){
            double scale = limit / length();
            mult2(scale);
            return this;
        }
        return this;
    }

    public void norm(){
        x = x / length();
        y = y / length();
    }

    public Vector2 norm2(){
        return new Vector2(x / length(), y / length());
    }

    public Vector2 reverse2(){
        return new Vector2(-x, -y);
    }

    public void add(Vector2 v) {
        x += v.x;
        y += v.y;
        //fixed();
    }

    public Vector2 add2(Vector2 v) {
        return new Vector2(x + v.x, y + v.y);
    }

    public void sub(Vector2 v) {
        x -= v.x;
        y -= v.y;
    }
    public Vector2 sub2(Vector2 v) {
        return new Vector2(x - v.x, y - v.y);
    }

    public void mult(double scalar){
        x *= scalar;
        y *= scalar;
    }


    public Vector2 mult2(double scalar) {
        return new Vector2(x * scalar, y * scalar);
        //{ x: (a.x * b).fixed(), y: (a.y * b).fixed() };
    }
    public double dot(Vector2 v){
        return x * v.x + y * v.y;
    }

    public double length() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public double length2(Vector2 v) {
        return Math.sqrt(Math.pow(x - v.x, 2) + Math.pow(y - v.y, 2));
    }

    public double lerp(double p, double n, String t) {
        double _t = Double.parseDouble(t);
        _t = Math.max(0, Math.min(1, _t));
        return (p + _t * (n - p));
    }

    public Vector2 lerp2(Vector2 v, Vector2 tv, String t) {
        return  new Vector2(lerp(v.x, tv.x, t), lerp(v.y, tv.y, t));
    }
}
