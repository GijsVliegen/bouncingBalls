import java.awt.*;

public class Point {
    double posX;
    double posY;

    public Point(double x, double y){
        this.posX = x;
        this.posY = y;
    }

    boolean isHigher(Point other){
        return this.posY > other.posY;
    }

    public double getWidthDiff(Point other){
        return this.posX - other.posX;
    }

    public double getHeightDiff(Point other){
        return this.posY - other.posY;
    }

    public Point copy(){
        return new Point(this.posX, this.posY);
    }

    public void plusWidth(double x){
        this.posX += x;
    }

    public void plusHeight(double y){
        this.posY += y;
    }

    double getDistance(Point other){
        return Math.sqrt(Math.pow((other.posX - this.posX), 2) + Math.pow((other.posY - this.posY), 2));
    }
}
