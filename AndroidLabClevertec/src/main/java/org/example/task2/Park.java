package org.example.task2;

import java.util.ArrayList;
import java.util.List;

public class Park {
    public static int profit = 0;
    public static int expenses = 0;
    public static List<Transport> transports = new ArrayList<>();

    public static void startWork() {
        System.out.println("All transport working:\n" + transports.toString());

        for (Transport machine :
                transports) {
            machine.goWork();
        }
    }

}
