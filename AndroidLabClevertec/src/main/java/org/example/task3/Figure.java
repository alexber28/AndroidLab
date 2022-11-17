package org.example.task3;

import java.util.ArrayList;
import java.util.Arrays;

public class Figure {
    private Point[] coordinates;
    private int offsetX;
    private int offsetY;


    public Figure(Point[] coordinates, int offsetX, int offsetY) {
        this.coordinates = coordinates;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public Point[] getCoordinates() {
        return coordinates;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public static void coordinateTransformation(ArrayList<Figure> figures) {
        for (Figure figure:
             figures) {
            for (int i = 0; i < 4; i++) {
                figure.coordinates[i].setX(figure.coordinates[i].getX() + figure.getOffsetX());
                figure.coordinates[i].setY(figure.coordinates[i].getY() + figure.getOffsetY());
            }
        }

    }

    @Override
    public String toString() {
        return "Figure{" +
                "coordinates=" + Arrays.toString(coordinates) +
                ", offsetX=" + offsetX +
                ", offsetY=" + offsetY +
                '}';
    }
}
