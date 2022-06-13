public class Ball {
    Point pos;
    double speedX;
    double speedY;
    double radius;

    public Ball(double x, double y, double speedX, double speedY, double radius){
        pos = new Point(x, y);
        this.speedX = speedX;
        this.speedY = speedY;
        this.radius = radius;
    }

    public double getWeight(){
        return this.radius*this.radius*Math.PI; //weight = oppervlakte
    }

    Point getPos(){
        return pos;
    }

    public double getWidthDiff(Ball other){
        return this.getPos().getWidthDiff(other.getPos());
    }

    public double getHeightDiff(Ball other){
        return this.getPos().getHeightDiff(other.getPos());
    }

    public double getDistance(Ball other){
        return this.getPos().getDistance(other.getPos());
    }

    public boolean isThereACollision(Ball other){
        return getDistance(other) <= this.radius + other.radius;
    }

    public Point handleCollision(Ball other){
        Point collisionPoint = adjustAfterCollision(other);
        setSpeedAfterCollision(collisionPoint);
        other.setSpeedAfterCollision(collisionPoint);
        return collisionPoint;
    }

    public Point adjustAfterCollision(Ball other){
        if (!isThereACollision(other)){ //gewoon veiligheidscheckje
            System.out.println("oei er was eigenlijk geen collision");
            return new Point(0, 0); //zonder deze regel zou er in dit geval een oneindige loop zijn
        }
        if (this.getPos().isHigher(other.getPos())){
            double dist = getDistance(other);
            double distToMoveBack = this.radius + other.radius - this.radius;
            double thisRadiusToTotalRatio = this.radius/(this.radius + other.radius);
            double otherRadiusToTotalRatio = other.radius/(this.radius + other.radius);
            double XToDistRatio = getWidthDiff(other)/dist;
            double YToDistRatio = getHeightDiff(other)/dist; //atlijd positief omdat this boven other ligt.
            pos.plusWidth(-distToMoveBack*thisRadiusToTotalRatio*XToDistRatio);
            pos.plusHeight(-distToMoveBack*thisRadiusToTotalRatio*YToDistRatio);
            other.pos.plusWidth(distToMoveBack*otherRadiusToTotalRatio*XToDistRatio);
            other.pos.plusHeight(distToMoveBack*otherRadiusToTotalRatio*YToDistRatio);
            //oei, nu nog collision point berekenen?
            //-> nu makkelijker
            Point collisionPoint = pos.copy();
            collisionPoint.plusWidth(radius*XToDistRatio);
            collisionPoint.plusHeight(radius*YToDistRatio);
            return collisionPoint;
        }
        else{
            return other.adjustAfterCollision(this);
        }
    }

    public void setSpeedAfterCollision(Point collisionPoint){
        //eerst de normale implementeren:
        //de hoek tussen de inkomende snelheid, en de vector (pos, collisionPoint);
    }

}
