package com.model;

public class VehicleSpace {
    private String spaceNumber;
    private boolean isOccupied;
    private Vehicle vehicle;

    public VehicleSpace(String spaceNumber) {
        this.spaceNumber = spaceNumber;
        this.isOccupied = false;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void occupy(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isOccupied = true;
    }

    public void vacate() {
        this.vehicle = null;
        this.isOccupied = false;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String getSpaceNumber() {
        return spaceNumber;
    }
}
