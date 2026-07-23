package com.egor.basic.hotel.controller;

import com.egor.basic.hotel.annotations.Annotations.*;
import com.egor.basic.hotel.model.Role;

import java.util.Scanner;

@Controller
public class DispatcherController {

    @Autowired
    private GuestController guestController;
    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private RoomController roomController;

    private Scanner sc = new Scanner(System.in);

    public void start() {

        while (true) {

            showMenu();

            try {
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1 -> addRoom();

                    case 2 -> checkIn();

                    case 3 -> checkOut();

                    case 4 -> showRooms();

                    case 5 -> assignRole();

                    case 6 -> showEmployees();

                    case 7 -> showGuests();

                    case 8 -> exit();

                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }catch(NumberFormatException e) {
                System.out.println("Error: Write a number between 1 and 7!");
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
        System.out.println("6. Show employees");
        System.out.println("7. Show guests");
        System.out.println("8. Exit");
        System.out.print("Enter your choice -> ");
    }

    private void showEmployees() {
        employeeController.showEmployee();
    }

    private void addRoom() {
        System.out.print("Enter room number: ");
        int roomNumber = Integer.parseInt(sc.nextLine());
        roomController.addRoom(roomNumber);
        System.out.println("Room added successfully.");
    }

    private void checkIn() {
        System.out.print("Enter guest name: ");
        String name = sc.nextLine();
        guestController.checkInGuest(name);
        System.out.println("Guest checked in successfully.");
    }

    private void checkOut() {
        System.out.print("Enter room number: ");
        int roomNumber = Integer.parseInt(sc.nextLine());
        guestController.checkOutGuest(roomNumber);
        System.out.println("Guest checked out successfully.");
    }

    private void showRooms() {
        roomController.showRooms();
    }

    private void showGuests() {
        guestController.showGuests();
    }

    private void assignRole() {

        System.out.print("Enter employee ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter employee Name: ");
        String name = sc.nextLine();

        System.out.print("Enter role (ADMIN/ MANAGER/ STAFF): ");
        String role = sc.nextLine();

        employeeController.assignRole(id, Role.valueOf(role), name);
    }

    private void exit() {
        System.out.println("Exiting...");
        System.exit(0);
    }


}
