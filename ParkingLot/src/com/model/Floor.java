package com.model;

import java.util.HashMap;
import java.util.Map;

public class Floor {
    private int floorNumber;
    private Map<String, VehicleSpace[]> vehicleSpaces;

    public Floor(int floorNumber, int carSpaces, int truckSpaces, int busSpaces, int bikeSpaces, int scSpaces) {
        this.floorNumber = floorNumber;
        vehicleSpaces = new HashMap<>();
        vehicleSpaces.put("Car", new VehicleSpace[carSpaces]);
        vehicleSpaces.put("Truck", new VehicleSpace[truckSpaces]);
        vehicleSpaces.put("Bus", new VehicleSpace[busSpaces]);
        vehicleSpaces.put("Bike", new VehicleSpace[bikeSpaces]);
        vehicleSpaces.put("SportsCar", new VehicleSpace[scSpaces]);

        initializeSpaces("Car", carSpaces);
        initializeSpaces("Truck", truckSpaces);
        initializeSpaces("Bus", busSpaces);
        initializeSpaces("Bike", bikeSpaces);
        initializeSpaces("SportsCar", scSpaces);
    }

    private void initializeSpaces(String type, int count) {
        for (int i = 0; i < count; i++) {
            vehicleSpaces.get(type)[i] = new VehicleSpace(type + "-" + floorNumber + "-" + (i + 1));
        }
    }

    public VehicleSpace[] getSpaces(String type) {
        return vehicleSpaces.get(type);
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public Map<String, VehicleSpace[]> getAllSpaces() {
        return vehicleSpaces;
    }
}
