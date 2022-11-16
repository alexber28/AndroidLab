package org.example.task2;

import java.util.Arrays;
import java.util.List;

public enum TrunkType {
    AWNING_TRUNK(List.of(OrderType.MANUFACTURED_GOODS)),
    REFRIGERATOR(List.of(OrderType.MANUFACTURED_GOODS, OrderType.FOOD)),
    TANK(List.of(OrderType.LIQUID));

    private List<OrderType> orderType;

    TrunkType(List<OrderType> orderType) {
        this.orderType = orderType;
    }

    public static List<OrderType> getOrderType(TrunkType trunkType) {
        return trunkType.orderType;
    }
}
