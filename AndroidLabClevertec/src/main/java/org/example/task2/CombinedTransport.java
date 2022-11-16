package org.example.task2;

import java.util.ArrayList;

public class CombinedTransport extends Transport {
    public static final int COST_OF_TECHNICAL_INSPECTION = 400;
    public static final int TRIPS_BEFORE_INSPECTION = 6;
    private static final int TRIPS_BEFORE_REPAIR = 8;
    public static final int TRIPS_BEFORE_REFUEL = 3;
    public static final int COST_OF_REPAIR = 2100;

    private int volumeOfTrunk;
    private int occupiedVolume;
    private int loadCapacity;
    private int occupiedCapacity;
    private TrunkType trunkType;
    private int passengerCapacity;
    private int currentCapacity;
    private int tripsAfterInspection;

    public CombinedTransport(int yearOfRelease, String manufacturer, FuelSupply fuel,
                             int fuelConsumption, int volumeOfTrunk, int loadCapacity,
                             int passengerCapacity, TrunkType trunkType) {
        super(yearOfRelease, manufacturer, fuel, fuelConsumption);
        this.volumeOfTrunk = volumeOfTrunk;
        this.occupiedVolume = 0;
        this.loadCapacity = loadCapacity;
        this.occupiedCapacity = 0;
        this.trunkType = trunkType;
        this.passengerCapacity = passengerCapacity;
        this.currentCapacity = 0;
        this.tripsAfterInspection = 0;
    }

    public int getOccupiedVolume() {
        return occupiedVolume;
    }

    public void setOccupiedVolume(int occupiedVolume) {
        this.occupiedVolume = occupiedVolume;
    }

    public int getOccupiedCapacity() {
        return occupiedCapacity;
    }

    public void setOccupiedCapacity(int occupiedCapacity) {
        this.occupiedCapacity = occupiedCapacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    @Override
    public String toString() {
        return "CombinedTransport{" + super.toString() +
                ", volumeOfTrunk=" + volumeOfTrunk +
                ", occupiedVolume=" + occupiedVolume +
                ", loadCapacity=" + loadCapacity +
                ", occupiedCapacity=" + occupiedCapacity +
                ", trunkType=" + trunkType +
                ", passengerCapacity=" + passengerCapacity +
                ", currentCapacity=" + currentCapacity +
                "}\n";
    }

    @Override
    public void refuel() {
        System.out.println("Combined transport is getting refueled");
        Park.expenses += FuelSupply.getCostPerLiter(getFuel()) * getFuelConsumption();
        setTripsAfterRefuel(0);
        Utils.sleep(2);
    }

    @Override
    public void repair() {
        System.out.println("Combined transport is getting repaired");
        Park.expenses += COST_OF_REPAIR;
        setTripsAfterRepair(0);
        Utils.sleep(3);
    }

    public void technicalInspection() {
        System.out.println("Combined transport is getting technical inspection");
        Park.expenses += COST_OF_TECHNICAL_INSPECTION;
        tripsAfterInspection = 0;
        Utils.sleep(2);
    }

    @Override
    public boolean isReadyForWork() {
        if (getTripsAfterRepair() < TRIPS_BEFORE_REPAIR || tripsAfterInspection < TRIPS_BEFORE_INSPECTION || getTripsAfterRefuel() < TRIPS_BEFORE_REFUEL) {
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
                    if (tripsAfterInspection == TRIPS_BEFORE_INSPECTION) {
                        technicalInspection();
                    }
                    if (getTripsAfterRefuel() == TRIPS_BEFORE_REFUEL) {
                        refuel();
                    }
                    doOrder(order);
                }
            }
            deleteCompletedOrders();
        }
        System.out.println("Combined transport ended working");
    }

    @Override
    public void doOrder(Order order) {
        ArrayList<Order> temp;

        occupiedVolume += order.getVolume();
        occupiedCapacity += order.getWeight();
        currentCapacity += order.getNumberOfPassengers();
        setTripsAfterRepair(getTripsAfterRepair() + 1);
        setTripsAfterRefuel(getTripsAfterRefuel() + 1);
        tripsAfterInspection++;

        Park.profit += order.getCostOfOrder();

        order.setDone(true);

        Utils.sleep(2);
    }

    @Override
    public boolean loadOrder(Order order) {
        if (order.getOrderType() != null) {
            return loadCargoOrder(order);
        } else {
            return loadPassengerOrder(order);
        }
    }

    public boolean loadCargoOrder(Order order) {
        if (TrunkType.getOrderType(trunkType).contains(order.getOrderType()) &&
                (order.getWeight() < loadCapacity - occupiedCapacity) &&
                (order.getVolume() < volumeOfTrunk - occupiedVolume)) {

            ArrayList<Order> temp;
            temp = getOrders();
            temp.add(order);
            setOrders(temp);

            order.setTransport(this);

            occupiedVolume += order.getVolume();
            occupiedCapacity += order.getWeight();

            System.out.println("Order to combined transport added");
            return true;
        } else {
            System.out.println("This order does not meet the requirements");
            return false;
        }
    }

    public boolean loadPassengerOrder(Order order) {
        if (order.getNumberOfPassengers() < passengerCapacity - currentCapacity) {

            ArrayList<Order> temp;
            temp = getOrders();
            temp.add(order);
            setOrders(temp);

            order.setTransport(this);

            currentCapacity += order.getNumberOfPassengers();

            System.out.println("Order to combined transport added");
            return true;
        } else {
            System.out.println("This order does not meet the requirements");
            return false;
        }
    }


    public int numberOfAvailableSeats() {
        return this.passengerCapacity - this.currentCapacity;
    }

    public int getEmptySpace() {
        return this.loadCapacity - this.occupiedCapacity;
    }

    public int getEmptyVolume() {
        return this.volumeOfTrunk - this.occupiedVolume;
    }
}
