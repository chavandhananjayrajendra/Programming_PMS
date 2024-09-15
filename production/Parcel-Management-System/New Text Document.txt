import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        BookingService bookingService = new BookingService();
        boolean exit = false;

        while (!exit) {
            System.out.println("Menu:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    userService.register(scanner);
                    break;
                case 2:
                    String role = userService.login(scanner);
                    if (role != null) {
                        if (role.equals("Customer")) {
                            handleCustomerMenu(scanner, bookingService);
                        } else if (role.equals("Officer")) {
                            handleOfficerMenu(scanner, bookingService);
                        }
                    } else {
                        System.out.println("Login failed.");
                    }
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }

    private static void handleCustomerMenu(Scanner scanner, BookingService bookingService) {
        boolean loggedIn = true; // This should be set to false upon logout

        while (loggedIn) {
            System.out.println("Customer Menu:");
            System.out.println("1. Booking Service");
            System.out.println("2. Tracking Status");
            System.out.println("3. Previous Booking");
            System.out.println("4. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    bookingService.bookService(scanner, "Customer");
                    break;
                case 2:
                    bookingService.trackStatus(scanner, "Customer");
                    break;
                case 3:
                    bookingService.viewBookingHistory(scanner, "Customer");
                    break;
                case 4:
                    loggedIn = false;
                    System.out.println("Logged out.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void handleOfficerMenu(Scanner scanner, BookingService bookingService) {
        boolean loggedIn = true; // This should be set to false upon logout

        while (loggedIn) {
            System.out.println("Officer Menu:");
            System.out.println("1. Booking Service");
            System.out.println("2. Tracking Status");
            System.out.println("3. Delivery Status Update");
            System.out.println("4. Pickup Scheduling");
            System.out.println("5. Previous Booking");
            System.out.println("6. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    bookingService.bookService(scanner, "Officer");
                    break;
                case 2:
                    bookingService.trackStatus(scanner, "Officer");
                    break;
                case 3:
                    bookingService.updateDeliveryStatus(scanner);
                    break;
                case 4:
                    bookingService.updatePickupSchedule(scanner);
                    break;
                case 5:
                    bookingService.viewBookingHistory(scanner, "Officer");
                    break;
                case 6:
                    loggedIn = false;
                    System.out.println("Logged out.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
