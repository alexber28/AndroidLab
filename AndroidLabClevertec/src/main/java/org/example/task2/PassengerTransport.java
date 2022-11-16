package org.example.task2;

import java.util.ArrayList;

public class PassengerTransport extends Transport {
    public static final int TRIPS_BEFORE_DISINFECTION = 2;
    private static final int TRIPS_BEFORE_REPAIR = 8;
    public static final int TRIPS_BEFORE_REFUEL = 3;
    public static final int COST_OF_DISINFECTION = 30;
    public static final int COST_OF_REPAIR = 2100;

    private int passengerCapacity;
    private int currentCapacity;
    private int tripsAfterDisinfection;

    public PassengerTransport(int yearOfRelease, String manufacturer, FuelSupply fuel,
                              int fuelConsumption, int passengerCapacity) {
        super(yearOfRelease, manufacturer, fuel, fuelConsumption);
        this.passengerCapacity = passengerCapacity;
        this.currentCapacity = 0;
        this.tripsAfterDisinfection = 0;
    }

    @Override
    public String toString() {
        return "PassengerTransport{" + super.toString() +
                ", passengerCapacity=" + passengerCapacity +
                ", currentCapacity=" + currentCapacity +
                "}\n";
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    @Override
    public void refuel() {
        System.out.println("Passenger transport is getting refueled");
        Park.expenses += FuelSupply.getCostPerLiter(getFuel()) * getFuelConsumption();
        setTripsAfterRefuel(0);
        Utils.sleep(2);
    }

    @Override
    public void repair() {
        System.out.println("Passenger transport is getting repaired");
        Park.expenses += COST_OF_REPAIR;
        setTripsAfterRepair(0);
        Utils.sleep(3);
    }

    public void disinfectTransport() {
        System.out.println("Passenger transport is getting disinfect");
        Park.expenses += COST_OF_DISINFECTION;
        tripsAfterDisinfection = 0;
        Utils.sleep(1);
    }

    @Override
    public boolean isReadyForWork() { //реализовать тут
        if (getTripsAfterRepair() < TRIPS_BEFORE_REPAIR || tripsAfterDisinfection < TRIPS_BEFORE_DISINFECTION || getTripsAfterRefuel() < TRIPS_BEFORE_REFUEL) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void goWork() {
        if (getOrders() != null) {
            for (Order order : getOrders()) {
                if (this.isReadyForWork()) {
                    doOrder(order);
                } else {
                    if (getTripsAfterRepair() == TRIPS_BEFORE_REPAIR) {
                        repair();
                    }
                    if (tripsAfterDisinfection == TRIPS_BEFORE_DISINFECTION) {
                        disinfectTransport();
                    }
                    if (getTripsAfterRefuel() == TRIPS_BEFORE_REFUEL) {
                        refuel();
                    }
                    doOrder(order);
                }
            }
            deleteCompletedOrders();
        }
        System.out.println("Passenger transport ended working");
    }

    @Override
    public void doOrder(Order order) {
        ArrayList<Order> temp;

        currentCapacity += order.getNumberOfPassengers();
        setTripsAfterRepair(getTripsAfterRepair() + 1);
        setTripsAfterRefuel(getTripsAfterRefuel() + 1);
        tripsAfterDisinfection++;

        Park.profit += order.getCostOfOrder();

        order.setDone(true);

        Utils.sleep(2);
    }

    @Override
    public boolean loadOrder(Order order) {
        if (order.getNumberOfPassengers() < passengerCapacity - currentCapacity) {

            ArrayList<Order> temp;
            temp = getOrders();
            temp.add(order);
            setOrders(temp);

            order.setTransport(this);

            currentCapacity += order.getNumberOfPassengers();

            System.out.println("Order to passenger transport added");
            return true;
        } else {
            System.out.println("This order does not meet the requirements");
            return false;
        }
    }

    public int numberOfAvailableSeats() {
        return this.passengerCapacity - this.currentCapacity;
    }
}
