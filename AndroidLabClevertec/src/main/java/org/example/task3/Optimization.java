package org.example.task3;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Optimization {
    public static ArrayList<Segment> segments = new ArrayList<>();
    public static ArrayList<Figure> figures = new ArrayList<>();

    public static void startWork() throws IOException {
        loadSegments();
        loadFigures();
        Figure.coordinateTransformation(figures);
        splitIntoSegments(figures);
        reduceRepeatingSegments();
        searchAlgorithm();
    }

    private static void splitIntoSegments(ArrayList<Figure> figures) {
        for (Figure figure :
                figures) {
            for (int i = 0; i < 3; i++) {
                addToSegments(figure.getCoordinates()[i], figure.getCoordinates()[i + 1]);
            }
            addToSegments(figure.getCoordinates()[3], figure.getCoordinates()[0]);
        }
    }

    private static void addToSegments(Point coordinate, Point coordinate1) {
        Segment segment = new Segment(coordinate, coordinate1);
        segments.add(segment);
    }

    private static void reduceRepeatingSegments() {
        ArrayList<Segment> tempSegments;
        tempSegments = segments;
        for (int i = 0; i < segments.size(); i++) {
            for (int j = i + 1; j < segments.size(); j++) {
                if (segments.get(i).getK() == segments.get(j).getK() && segments.get(i).getB() == segments.get(j).getB() && tempSegments.contains(segments.get(i))) {
                    tempSegments.remove(segments.get(j));
                }
            }
        }
        segments = tempSegments;
    }

    private static void searchAlgorithm() throws IOException {
        Point startingPoint = new Point(0, 0);
        FileWriter nFile = new FileWriter("Answer.txt");

        Segment minDistanceSegment = segments.get(0);
        while (segments.size() > 0) {
            double minimalDistance = Double.MAX_VALUE, distance;
            boolean isSecondPoint = false;

            for (Segment segment :
                    segments) {
                distance = Point.countDistance(startingPoint, segment.getStartPoint());

                if (distance < minimalDistance) {
                    minimalDistance = distance;
                    minDistanceSegment = segment;
                    isSecondPoint = false;
                }

                distance = Point.countDistance(startingPoint, segment.getEndPoint());

                if (distance < minimalDistance) {
                    minimalDistance = distance;
                    minDistanceSegment = segment;
                    isSecondPoint = true;
                }
            }

            if (isSecondPoint) {
                minDistanceSegment.changePoints();
            }

            startingPoint = minDistanceSegment.getEndPoint();
            nFile.write(minDistanceSegment.getStartPoint().getX() + ", " +
                    minDistanceSegment.getStartPoint().getY() + ", " +
                    minDistanceSegment.getEndPoint().getX() + ", " +
                    minDistanceSegment.getEndPoint().getY() + "\n");
            segments.remove(minDistanceSegment);
        }

        nFile.close();
    }

    private static void loadFigures() {
        try {
            FileReader fr = new FileReader("Figures.txt");
            Scanner scan = new Scanner(fr);

            while (scan.hasNextLine()) {
                Point[] coordinates = new Point[4];

                for (int j = 0; j < 4; j++) {
                    String[] numbers = scan.nextLine().split(", ");
                    Point point = new Point(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]));
                    coordinates[j] = point;
                }

                String[] offsetNumbers = scan.nextLine().split(", ");
                Figure figure = new Figure(coordinates, Integer.parseInt(offsetNumbers[0]), Integer.parseInt(offsetNumbers[1]));

                figures.add(figure);
            }

            fr.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void loadSegments() {
        try {
            FileReader fr = new FileReader("Segments.txt");
            Scanner scan = new Scanner(fr);

            while (scan.hasNextLine()) {
                String[] numbers = scan.nextLine().split(", ");
                Segment segment = new Segment(new Point(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1])), new Point(Integer.parseInt(numbers[2]), Integer.parseInt(numbers[3])));
                segments.add(segment);
            }

            fr.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
