import com.model.ParkingLot;
import com.model.Vehicle;
import com.model.strategy.CostStrategy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter number of floors: ");
            int floors = scanner.nextInt();
            System.out.print("Enter number of spaces per floor for Cars: ");
            int carSpaces = scanner.nextInt();
            System.out.print("Enter number of spaces per floor for Trucks: ");
            int truckSpaces = scanner.nextInt();
            System.out.print("Enter number of spaces per floor for Buses: ");
            int busSpaces = scanner.nextInt();
            System.out.print("Enter number of spaces per floor for Bikes: ");
            int bikeSpaces = scanner.nextInt();
            System.out.print("Enter number of spaces per floor for SportsCars: ");
            int scSpaces = scanner.nextInt();
            ParkingLot parkingLot = new ParkingLot(floors, carSpaces, truckSpaces, busSpaces, bikeSpaces, scSpaces);
            CostStrategy costStrategy = new CostStrategy();
            costStrategy.setCost("Car", 20);
            costStrategy.setCost("Truck", 30);
            costStrategy.setCost("Bus", 30);
            costStrategy.setCost("Bike", 10);
            costStrategy.setCost("SportsCar", 50);
            parkingLot.setCostStrategy(costStrategy);

            while (true) {
                System.out.println("\n1. Add Vehicle\n2. Remove Vehicle\n3. Check Availability\n4. Display Status\n5. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addVehicle(scanner, parkingLot);
                        break;
                    case 2:
                        removeVehicle(scanner, parkingLot);
                        break;
                    case 3:
                        checkAvailability(scanner, parkingLot);
                        break;
                    case 4:
                        parkingLot.displayStatus();
                        break;
                    case 5:
                        System.exit(0);
                    default:
                        System.out.println("Invalid option, please try again.");
                }
            }
        }catch (Exception e){
          e.printStackTrace();
        }
    }

    private static void addVehicle(Scanner scanner, ParkingLot parkingLot) {
        System.out.print("Enter vehicle type (Car/Truck/Bus/Bike/SportsCar): ");
        String type = scanner.next();
        System.out.print("Enter registration number: ");
        String regNumber = scanner.next();
        System.out.print("Enter color: ");
        String color = scanner.next();
        Vehicle vehicle = new Vehicle(type, regNumber, color);
        String token = parkingLot.addVehicle(vehicle);
        System.out.println(token);
    }

    private static void removeVehicle(Scanner scanner, ParkingLot parkingLot) {
        System.out.print("Enter token ID to remove vehicle: ");
        String token = scanner.next();
        String result = parkingLot.removeVehicle(token);
        System.out.println(result);
    }

    private static void checkAvailability(Scanner scanner, ParkingLot parkingLot) {
        System.out.print("Enter floor number: ");
        int floor = scanner.nextInt();
        System.out.print("Enter vehicle type (Car/Truck/Bus/Bike/SportsCar): ");
        String type = scanner.next();
        boolean isAvailable = parkingLot.checkAvailability(floor, type);
        System.out.println("Availability: " + (isAvailable ? "Available" : "Not Available"));
    }
}
