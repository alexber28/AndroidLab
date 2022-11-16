package org.example.task2;

import java.util.ArrayList;

public class Order {
    private String startingPoint;
    private String endingPoint;
    private int costOfOrder;
    private int id;
    private int volume;
    private int weight;
    private int numberOfPassengers;
    private OrderType orderType;
    private Transport transport;
    private boolean isDone;

    private static int counterId = 0;
    public static ArrayList<Order> allOrders = new ArrayList<>();

    public Order(String startingPoint, String endingPoint, int costOfOrder, int numberOfPassengers) {
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.costOfOrder = costOfOrder;
        this.id = counterId++;
        this.numberOfPassengers = numberOfPassengers;
        this.transport = null;
        this.isDone = false;
        this.orderType = null;
        allOrders.add(this);
    }

    public Order(String startingPoint, String endingPoint, int costOfOrder,
                 int volume, int weight, OrderType orderType) {
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.costOfOrder = costOfOrder;
        this.id = counterId++;
        this.volume = volume;
        this.weight = weight;
        this.orderType = orderType;
        this.transport = null;
        this.isDone = false;
        allOrders.add(this);
    }

    @Override
    public String toString() {
        return "Order{" +
                "isDone=" + isDone +
                ", startingPoint='" + startingPoint + '\'' +
                ", endingPoint='" + endingPoint + '\'' +
                ", costOfOrder=" + costOfOrder +
                ", id=" + id +
                ", volume=" + volume +
                ", weight=" + weight +
                ", numberOfPassengers=" + numberOfPassengers +
                ", orderType=" + orderType +
                ", transport=" + transport +
                ", isDone=" + isDone +
                '}';
    }

    public int getCostOfOrder() {
        return costOfOrder;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isDone() {
        return isDone;
    }

    public int getVolume() {
        return volume;
    }

    public int getWeight() {
        return weight;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public int getId() {
        return id;
    }

    public static void viewAllOrders() {
        for (Order order :
                allOrders) {
            System.out.println(order);
        }
    }
}
