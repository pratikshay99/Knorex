package com.model;

import com.model.strategy.CostStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private Floor[] floors;
    private CostStrategy costStrategy;
    private List<Vehicle> parkedVehicles;

    public ParkingLot(int numOfFloors, int carSpaces, int truckSpaces, int busSpaces, int bikeSpaces, int scSpaces) {
        floors = new Floor[numOfFloors];
        for (int i = 0; i < numOfFloors; i++) {
            floors[i] = new Floor(i + 1, carSpaces, truckSpaces, busSpaces,bikeSpaces,scSpaces);
        }
        parkedVehicles = new ArrayList<>();
    }

    public void setCostStrategy(CostStrategy costStrategy) {
        this.costStrategy = costStrategy;
    }

    public String addVehicle(Vehicle vehicle) {
        for (Floor floor : floors) {
            VehicleSpace[] spaces = floor.getSpaces(vehicle.getType());
            for (VehicleSpace space : spaces) {
                if (!space.isOccupied()) {
                    space.occupy(vehicle);
                    parkedVehicles.add(vehicle);
                    return "Vehicle added. Token ID: " + generateToken(vehicle); // Generate and return a unique token
                }
            }
        }
        return "Parking is full for vehicle type: " + vehicle.getType();
    }

    public String removeVehicle(String token) {
        for (Vehicle vehicle : parkedVehicles) {
            if (vehicle.getRegistrationNumber().equals(token)) {
                for (Floor floor : floors) {
                    VehicleSpace[] spaces = floor.getSpaces(vehicle.getType());
                    for (VehicleSpace space : spaces) {
                        if (space.isOccupied() && space.getVehicle().equals(vehicle)) {
                            space.vacate();
                            parkedVehicles.remove(vehicle);
                            return "Vehicle removed successfully: " + vehicle.getRegistrationNumber();
                        }
                    }
                }
            }
        }
        return "No vehicle found with token ID: " + token;
    }

    public boolean checkAvailability(int floorNumber, String vehicleType) {
        if (floorNumber < 1 || floorNumber > floors.length)
            return false;

        VehicleSpace[] spaces = floors[floorNumber - 1].getSpaces(vehicleType);
        for (VehicleSpace space : spaces) {
            if (!space.isOccupied()) {
                return true;
            }
        }
        return false;
    }

    public void displayStatus() {
        for (Floor floor : floors) {
            System.out.println("Floor " + floor.getFloorNumber() + ":");
            Map<String, VehicleSpace[]> allSpaces = floor.getAllSpaces();
            for (String type : allSpaces.keySet()) {
                VehicleSpace[] spaces = allSpaces.get(type);
                int occupied = 0;
                for (VehicleSpace space : spaces) {
                    if (space.isOccupied()) {
                        occupied++;
                    }
                }
                System.out.println("\t" + type + ": " + occupied + " occupied, " + (spaces.length - occupied) + " available");
            }
        }
    }


    private String generateToken(Vehicle vehicle) {
        return vehicle.getRegistrationNumber();
    }
}
