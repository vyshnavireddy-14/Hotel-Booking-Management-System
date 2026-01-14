import java.util.ArrayList;
import java.util.Scanner;

class Room {
    int roomNumber;
    boolean isBooked;
    String guestName;

    Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.isBooked = false;
        this.guestName = "";
    }

    public String toString() {
        if (isBooked) {
            return "Room " + roomNumber + " | Booked by: " + guestName;
        } else {
            return "Room " + roomNumber + " | Available";
        }
    }
}

// ---------- Hotel Class ----------
class Hotel {
    private ArrayList<Room> rooms;

    Hotel(int totalRooms) {
        rooms = new ArrayList<>();
        for (int i = 1; i <= totalRooms; i++) {
            rooms.add(new Room(i));
        }
    }

    void viewRooms() {
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    void viewAvailableRooms() {
        boolean available = false;
        for (Room room : rooms) {
            if (!room.isBooked) {
                System.out.println("Room " + room.roomNumber);
                available = true;
            }
        }
        if (!available) {
            System.out.println("No rooms available.");
        }
    }

    void bookRoom(int roomNumber, String guestName) {
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber) {
                if (room.isBooked) {
                    System.out.println("Room already booked.");
                } else {
                    room.isBooked = true;
                    room.guestName = guestName;
                    System.out.println("Room booked successfully.");
                }
                return;
            }
        }
        System.out.println("Invalid room number.");
    }

    void cancelBooking(int roomNumber) {
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber) {
                if (!room.isBooked) {
                    System.out.println("Room is not booked.");
                } else {
                    room.isBooked = false;
                    room.guestName = "";
                    System.out.println("Booking cancelled.");
                }
                return;
            }
        }
        System.out.println("Invalid room number.");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hotel hotel = new Hotel(10);

        while (true) {
            System.out.println("Hotel Booking Management System");
            System.out.println("1. View All Rooms");
            System.out.println("2. View Available Rooms");
            System.out.println("3. Book a Room");
            System.out.println("4. Cancel Booking");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    hotel.viewRooms();
                    break;

                case "2":
                    hotel.viewAvailableRooms();
                    break;

                case "3":
                    try {
                        System.out.print("Enter Room Number: ");
                        int roomNumber = Integer.parseInt(sc.nextLine());

                        System.out.print("Enter Guest Name: ");
                        String name = sc.nextLine();

                        if (name.isEmpty()) {
                            System.out.println("Guest name cannot be empty.");
                            break;
                        }

                        hotel.bookRoom(roomNumber, name);
                    } catch (NumberFormatException e) {
                        System.out.println("Room number must be numeric.");
                    }
                    break;

                case "4":
                    try {
                        System.out.print("Enter Room Number: ");
                        int roomNumber = Integer.parseInt(sc.nextLine());
                        hotel.cancelBooking(roomNumber);
                    } catch (NumberFormatException e) {
                        System.out.println("Room number must be numeric.");
                    }
                    break;

                case "5":
                    System.out.println("Thank you for using the system.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Select 1–5.");
            }
        }
    }
}