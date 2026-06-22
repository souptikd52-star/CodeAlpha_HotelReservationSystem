import java.util.*;
import java.io.*;
public class HotelReservationSystem {
    static ArrayList<Room> rooms = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        initializeRooms();
        loadBookings();
        int choice;
        do {
            System.out.println("\n===== HOTEL RESERVATION SYSTEM =====");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Search Room Category");
            System.out.println("3. Book Room");
            System.out.println("4. Cancel Reservation");
            System.out.println("5. View Booking Details");
            System.out.println("6. Exit");
            System.out.print("Enter Choice: ");
            choice = sc.nextInt();
            switch(choice) {
                case 1:
                    viewRooms();
                    break;
                case 2:
                    searchRoom();
                    break;
                case 3:
                    bookRoom();
                    break;
                case 4:
                    cancelReservation();
                    break;
                case 5:
                    viewBookings();
                    break;
                case 6:
                    saveBookings();
                    System.out.println("Thank You!");
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        } while(choice != 6);
    }
    static void initializeRooms() {
        rooms.add(new Room(101,"Standard",2000));
        rooms.add(new Room(102,"Standard",2000));
        rooms.add(new Room(201,"Deluxe",3500));
        rooms.add(new Room(202,"Deluxe",3500));
        rooms.add(new Room(301,"Suite",5000));
        rooms.add(new Room(302,"Suite",5000));
    }
    static void viewRooms() {
        System.out.println("\nAvailable Rooms:");
        for(Room room : rooms) {
            if(!room.isBooked()) {
                System.out.println(
                        "Room: " + room.getRoomNumber() +
                        " | Category: " + room.getCategory() +
                        " | Price: ₹" + room.getPrice());
            }
        }
    }
    static void searchRoom() {
        System.out.print("Enter Category (Standard/Deluxe/Suite): ");
        String category = sc.next();
        for(Room room : rooms) {
            if(room.getCategory().equalsIgnoreCase(category)
                    && !room.isBooked()) {

                System.out.println(
                        "Room: " + room.getRoomNumber() +
                        " Price: ₹" + room.getPrice());
            }
        }
    }
    static void bookRoom() {
        System.out.print("Enter Room Number: ");
        int roomNo = sc.nextInt();
        sc.nextLine();
        for(Room room : rooms) {
            if(room.getRoomNumber() == roomNo &&
                    !room.isBooked()) {

                System.out.print("Customer Name: ");
                String name = sc.nextLine();

                System.out.println(
                        "Amount to Pay: ₹" +
                                room.getPrice());

                System.out.print(
                        "Enter 'PAY' to simulate payment: ");

                String payment = sc.nextLine();

                if(payment.equalsIgnoreCase("PAY")) {

                    room.bookRoom(name);

                    saveBookings();

                    System.out.println(
                            "Booking Successful!");
                }

                return;
            }
        }

        System.out.println("Room unavailable.");
    }

    static void cancelReservation() {

        System.out.print("Enter Room Number: ");
        int roomNo = sc.nextInt();

        for(Room room : rooms) {

            if(room.getRoomNumber() == roomNo &&
                    room.isBooked()) {

                room.cancelBooking();

                saveBookings();

                System.out.println(
                        "Reservation Cancelled");
                return;
            }
        }

        System.out.println("Booking not found.");
    }

    static void viewBookings() {

        System.out.println("\nBooking Details:");

        for(Room room : rooms) {

            if(room.isBooked()) {

                System.out.println(
                        "Room: " + room.getRoomNumber() +
                        " | Customer: " +
                        room.getCustomerName() +
                        " | Category: " +
                        room.getCategory());
            }
        }
    }

    static void saveBookings() {

        try {

            PrintWriter pw =
                    new PrintWriter(
                            new FileWriter("bookings.txt"));

            for(Room room : rooms) {

                pw.println(room.toString());
            }

            pw.close();

        } catch(Exception e) {

            System.out.println(
                    "Error Saving File");
        }
    }

    static void loadBookings() {

        File file = new File("bookings.txt");

        if(!file.exists())
            return;

        try {

            Scanner fileScanner =
                    new Scanner(file);

            rooms.clear();

            while(fileScanner.hasNextLine()) {

                String[] data =
                        fileScanner.nextLine()
                                .split(",");

                Room room =
                        new Room(
                                Integer.parseInt(data[0]),
                                data[1],
                                Double.parseDouble(data[2]));

                if(Boolean.parseBoolean(data[3])) {

                    room.bookRoom(data[4]);
                }

                rooms.add(room);
            }

            fileScanner.close();

        } catch(Exception e) {

            System.out.println(
                    "Error Loading File");
        }
    }
}