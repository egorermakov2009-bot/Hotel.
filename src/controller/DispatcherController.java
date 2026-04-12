package controller;

import model.Role;

import java.util.Scanner;

public class DispatcherController {

    private GuestController guestController = new GuestController();
    private EmployeeController employeeController = new EmployeeController();
    private RoomController roomController = new RoomController();

    private Scanner sc = new Scanner(System.in);

    public void start() {

        while (true) {

            showMenu();

            int choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1 -> addRoom();

                    case 2 -> checkIn();

                    case 3 -> checkOut();

                    case 4 -> showRooms();

                    case 5 -> assignRole();

                    case 6 -> exit();

                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }catch(Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void showMenu() {
        System.out.println("\n=== HOTEL MENU ===");
        System.out.println("1. Add room");
        System.out.println("2. Check in guest");
        System.out.println("3. Check out guest");
        System.out.println("4. Show rooms");
        System.out.println("5. Assign role");
        System.out.println("6. Exit");
        System.out.print("Enter your choice -> ");
    }

    private void addRoom() {
        System.out.print("Enter room number: ");
        int roomNumber = sc.nextInt();
        roomController.addRoom(roomNumber);
        System.out.println("Room added successfully.");
    }

    private void checkIn() {
        System.out.print("Enter guest name: ");
        String name = sc.next();
        guestController.checkInGuest(name);
        System.out.println("Guest checked in successfully.");
    }

    private void checkOut() {
        System.out.print("Enter room number: ");
        int roomNumber = sc.nextInt();
        guestController.checkOutGuest(roomNumber);
        System.out.println("Guest checked out successfully.");
    }

    private void showRooms() {
        roomController.showRooms();
    }

    private void assignRole() {

        System.out.print("Enter employee ID: ");
        int id = sc.nextInt();

        System.out.print("Enter role (ADMIN/ MANAGER/ STAFF): )");
        String role = sc.next();

        employeeController.assignRole(id, Role.valueOf(role));
    }

    private void exit() {
        System.out.println("Exiting...");
        System.exit(0);
    }


}
