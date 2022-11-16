package org.example.task2;

public enum FuelSupply {
    PETROL(30), DIESEL(23), GAS(28);

    private int costPerLiter;

    FuelSupply(int costPerLiter) {
        this.costPerLiter = costPerLiter;
    }

    public static int getCostPerLiter(FuelSupply fuelSupply) {
        return fuelSupply.costPerLiter;
    }
}
