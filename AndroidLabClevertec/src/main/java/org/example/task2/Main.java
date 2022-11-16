package org.example.task2;

public class Main {
    public static void main(String[] args) {
        CombinedTransport travelBus = new CombinedTransport(1998, "BMW", FuelSupply.GAS, 9, 100, 150, 200, TrunkType.AWNING_TRUNK);
        PassengerTransport bus = new PassengerTransport(2010, "Audi", FuelSupply.PETROL, 7, 110);
        CargoTransport truck = new CargoTransport(2005, "Volvo", FuelSupply.DIESEL, 5, 120, 200, TrunkType.REFRIGERATOR);

        Park.transports.add(bus);
        Park.transports.add(truck);
        Park.transports.add(travelBus);

        Order order1 = new Order("Minsk", "Warsaw", 1500, 10, 15, OrderType.FOOD);
        Order order2 = new Order("Saint-Petersburg", "Berlin", 890, 10, 15, OrderType.MANUFACTURED_GOODS);
        Order order3 = new Order("Beijing", "Hong Kong", 1000, 10, 15, OrderType.LIQUID);
        Order order4 = new Order("Vancouver", "Washington DC", 2500, 10, 15, OrderType.FOOD);

        truck.refuel();
        truck.loadOrder(order1);
        System.out.println("Empty space in cargo transport: " + truck.getEmptySpace());
        truck.loadOrder(order2);
        System.out.println("Empty volume in cargo transport: " + truck.getEmptyVolume());
        truck.loadOrder(order3);
        truck.loadOrder(order4);
        truck.removeOneOrder(order2);

        truck.listOfAllOrders();

        Order order5 = new Order("Minsk", "Brest", 400, 55);
        Order order6 = new Order("Gomel", "Grodno", 450, 40);
        Order order7 = new Order("Vitebsk", "Mogilev", 200, 30);

        bus.repair();
        bus.loadOrder(order5);
        bus.loadOrder(order6);
        System.out.println("Available seats: " + bus.numberOfAvailableSeats());
        bus.loadOrder(order7);

        bus.listOfAllOrders();

        Order order8 = new Order("Minsk", "Brest", 400, 55);
        Order order9 = new Order("Riga", "Vilnius", 600, 90, 90, OrderType.MANUFACTURED_GOODS);
        Order order10 = new Order("Vitebsk", "Mogilev", 200, 30);

        travelBus.technicalInspection();
        System.out.println("Empty seats in travel bus: " + travelBus.numberOfAvailableSeats());
        travelBus.loadOrder(order8);
        System.out.println("Empty space in travel bus: " + travelBus.getEmptySpace());
        travelBus.loadOrder(order9);
        System.out.println("Empty volume in travel bus: " + travelBus.getEmptyVolume());
        travelBus.loadOrder(order10);

        Park.startWork();

        System.out.println("Profit: " + Park.profit);
        System.out.println("Expenses: " + Park.expenses);

        Order.viewAllOrders();

    }
}
