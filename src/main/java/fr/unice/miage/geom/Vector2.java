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

    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }

    public Vector2 copy() {
        return new Vector2(this.x, this.y);
    }

    public void limit(double limit){
        if (this.length() > limit){
            double scale = limit / this.length();
            this.mult(scale);
        }
    }

    public Vector2 limit2(double limit){
        if (this.length() > limit){
            double scale = limit / this.length();
            this.mult2(scale);
            return this;
        }
        return this;
    }

    public void norm(){
        this.x = this.x / this.length();
        this.y = this.y / this.length();
    }

    public Vector2 norm2(){
        return new Vector2(this.x / this.length(), this.y / this.length());
    }

    public Vector2 reverse2(){
        return new Vector2(-this.x, -this.y);
    }

    public void add(Vector2 v) {
        this.x += v.x;
        this.y += v.y;
        //this.fixed();
    }

    public Vector2 add2(Vector2 v) {
        return new Vector2(this.x + v.x, this.y + v.y);
    }

    public void sub(Vector2 v) {
        this.x -= v.x;
        this.y -= v.y;
    }
    public Vector2 sub2(Vector2 v) {
        return new Vector2(this.x - v.x, this.y - v.y);
    }

    public void mult(double scalar){
        this.x *= scalar;
        this.y *= scalar;
    }


    public Vector2 mult2(double scalar) {
        return new Vector2(this.x * scalar, this.y * scalar);
        //{ x: (a.x * b).fixed(), y: (a.y * b).fixed() };
    }
    public double dot(Vector2 v){
        return this.x * v.x + this.y * v.y;
    }

    public double length() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    public double length2(Vector2 v) {
        return Math.sqrt(Math.pow(this.x - v.x, 2) + Math.pow(this.y - v.y, 2));
    }

    public double lerp(double p, double n, String t) {
        double _t = Double.parseDouble(t);
        _t = Math.max(0, Math.min(1, _t));
        return (p + _t * (n - p));
    }

    public Vector2 lerp2(Vector2 v, Vector2 tv, String t) {
        return  new Vector2(this.lerp(v.x, tv.x, t), this.lerp(v.y, tv.y, t));
    }
}
