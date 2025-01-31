import java.util.*;

class Room {
    int roomNumber;
    String category;
    double price;
    boolean isBooked;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.isBooked = false;
    }

    public void bookRoom() {
        this.isBooked = true;
    }

    public void cancelBooking() {
        this.isBooked = false;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + category + ") - $" + price + ", Available: " + !isBooked;
    }
}

class Hotel {
    private List<Room> rooms;
    private Map<Integer, String> reservations;

    public Hotel() {
        rooms = new ArrayList<>();
        reservations = new HashMap<>();
        initializeRooms();
    }

    private void initializeRooms() {
        rooms.add(new Room(101, "Standard", 100));
        rooms.add(new Room(102, "Standard", 100));
        rooms.add(new Room(201, "Deluxe", 200));
        rooms.add(new Room(202, "Deluxe", 200));
        rooms.add(new Room(301, "Suite", 300));
    }

    public void displayAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (!room.isBooked) {
                System.out.println(room);
            }
        }
    }

    public boolean bookRoom(int roomNumber, String guestName) {
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber && !room.isBooked) {
                room.bookRoom();
                reservations.put(roomNumber, guestName);
                System.out.println("Room " + roomNumber + " booked successfully for " + guestName);
                return true;
            }
        }
        System.out.println("Room not available or invalid selection.");
        return false;
    }

    public void viewBookings() {
        System.out.println("Booking Details:");
        if (reservations.isEmpty()) {
            System.out.println("No reservations yet.");
        } else {
            for (Map.Entry<Integer, String> entry : reservations.entrySet()) {
                System.out.println("Room " + entry.getKey() + " - Booked by " + entry.getValue());
            }
        }
    }

    public boolean processPayment(int roomNumber, double amount) {
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber && room.isBooked && room.price == amount) {
                System.out.println("Payment of $" + amount + " for Room " + roomNumber + " successful.");
                return true;
            }
        }
        System.out.println("Invalid payment details.");
        return false;
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();
        
        while (true) {
            System.out.println("\nHotel Reservation System");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. View Bookings");
            System.out.println("4. Make Payment");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    hotel.displayAvailableRooms();
                    break;
                case 2:
                    System.out.print("Enter room number: ");
                    int roomNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter guest name: ");
                    String guestName = scanner.nextLine();
                    hotel.bookRoom(roomNumber, guestName);
                    break;
                case 3:
                    hotel.viewBookings();
                    break;
                case 4:
                    System.out.print("Enter room number: ");
                    int roomNum = scanner.nextInt();
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    hotel.processPayment(roomNum, amount);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
