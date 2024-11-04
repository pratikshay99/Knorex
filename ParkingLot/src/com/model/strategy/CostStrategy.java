package com.model.strategy;

import java.util.HashMap;
import java.util.Map;

public class CostStrategy {
    private Map<String, Integer> costs;

    public CostStrategy() {
        costs = new HashMap<>();
    }

    public void setCost(String vehicleType, int cost) {
        costs.put(vehicleType, cost);
    }

    public int getCost(String vehicleType) {
        return costs.getOrDefault(vehicleType, 0);
    }
}
