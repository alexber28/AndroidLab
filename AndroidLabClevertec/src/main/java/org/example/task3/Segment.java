package org.example.task3;

public class Segment {
    private Point startPoint;
    private Point endPoint;
    private double k; // y = k * x + b - straight line equation
    private double b; // to define if one segment is a part of another

    public Segment(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        if(startPoint.getX() == endPoint.getX()) {
            this.k = startPoint.getX();
            this.b = 0;
        } else {
            if(startPoint.getY() == endPoint.getY()) {
                this.k = 0;
                this.b = startPoint.getY();
            } else {
                this.k = (double) (startPoint.getY() - endPoint.getY()) / (startPoint.getX() - endPoint.getX());
                this.b = (double) startPoint.getY() - this.k * startPoint.getX();
            }
        }
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public double getK() {
        return k;
    }

    public double getB() {
        return b;
    }

    public void changePoints() {
        Point tempPoint;
        tempPoint = this.startPoint;
        this.startPoint = this.endPoint;
        this.endPoint = tempPoint;
    }

    @Override
    public String toString() {
        return "Segment{" +
                "startPoint=" + startPoint +
                ", endPoint=" + endPoint +
                ", k=" + k +
                ", b=" + b +
                '}';
    }
}
