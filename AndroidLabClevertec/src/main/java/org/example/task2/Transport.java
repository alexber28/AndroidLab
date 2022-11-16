package org.example.task2;

import java.util.ArrayList;

public abstract class Transport {
    private int transportId;
    private int yearOfRelease;
    private String manufacturer;
    private FuelSupply fuel;
    private int fuelConsumption;
    private int tasksDone;

    private int tripsAfterRepair;
    private int tripsAfterRefuel;
    private ArrayList<Order> orders;

    private static int counter = 0;

    public Transport(int yearOfRelease, String manufacturer, FuelSupply fuel, int fuelConsumption) {
        this.yearOfRelease = yearOfRelease;
        this.manufacturer = manufacturer;
        this.fuel = fuel;
        this.fuelConsumption = fuelConsumption;
        this.tasksDone = 0;
        this.tripsAfterRepair = 0;
        this.tripsAfterRefuel = 0;
        this.orders = new ArrayList<>();
        this.transportId = counter++;
    }

    @Override
    public String toString() {
        return "transportId=" + transportId +
                ", yearOfRelease=" + yearOfRelease +
                ", manufacturer='" + manufacturer + '\'' +
                ", fuel=" + fuel +
                ", fuelConsumption=" + fuelConsumption +
                ", tasksDone=" + tasksDone;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public FuelSupply getFuel() {
        return fuel;
    }

    public int getFuelConsumption() {
        return fuelConsumption;
    }

    public int getTripsAfterRepair() {
        return tripsAfterRepair;
    }

    public void setTripsAfterRepair(int tripsAfterRepair) {
        this.tripsAfterRepair = tripsAfterRepair;
    }

    public int getTripsAfterRefuel() {
        return tripsAfterRefuel;
    }

    public void setTripsAfterRefuel(int tripsAfterRefuel) {
        this.tripsAfterRefuel = tripsAfterRefuel;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public abstract void refuel();

    public abstract void repair();

    public abstract boolean isReadyForWork();

    public abstract void goWork();

    public abstract void doOrder(Order order);

    public abstract boolean loadOrder(Order order);

    public void resetAllOrders() {
        for (Order order :
                this.orders) {
            this.orders.remove(order);
        }
    }

    public void listOfAllOrders() {
        for (Order order :
                this.orders) {
            System.out.println(order);
        }
    }

    public void deleteCompletedOrders() {
        ArrayList<Order> temp = new ArrayList<>();
        for (Order order :
                this.orders) {
            if (!order.isDone()) {
                temp.add(order);
            }
        }
        setOrders(temp);
    }

    public void removeOneOrder(Order order) {
        if (orders.remove(order)) {
            System.out.println("Order id = " + order.getId() + " successfully removed");

            if (this instanceof CargoTransport) {
                ((CargoTransport) this).setOccupiedCapacity(((CargoTransport) this).getOccupiedCapacity() + order.getWeight());
                ((CargoTransport) this).setOccupiedVolume(((CargoTransport) this).getOccupiedVolume() + order.getVolume());
            } else {
                if (this instanceof PassengerTransport) {
                    ((PassengerTransport) this).setCurrentCapacity(((PassengerTransport) this).getCurrentCapacity() + order.getNumberOfPassengers());
                } else {
                    ((CombinedTransport) this).setOccupiedCapacity(((CombinedTransport) this).getOccupiedCapacity() + order.getWeight());
                    ((CombinedTransport) this).setOccupiedVolume(((CombinedTransport) this).getOccupiedVolume() + order.getVolume());
                    ((CombinedTransport) this).setCurrentCapacity(((CombinedTransport) this).getCurrentCapacity() + order.getNumberOfPassengers());
                }
            }
        } else {
            System.out.println("This order wasn't loaded");
        }
    }
}
