import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BookingService {
    private static class Booking {
        String bookingId, customerName, recName, recAddress, recMobile, weight, contents, deliveryType, packingPreference;
        Date pickupTime, dropoffTime;
        double serviceCost;
        Date paymentTime;

        Booking(String bookingId, String customerName, String recName, String recAddress, String recMobile, String weight, String contents,
                String deliveryType, String packingPreference, Date pickupTime, Date dropoffTime, double serviceCost, Date paymentTime) {
            this.bookingId = bookingId;
            this.customerName = customerName;
            this.recName = recName;
            this.recAddress = recAddress;
            this.recMobile = recMobile;
            this.weight = weight;
            this.contents = contents;
            this.deliveryType = deliveryType;
            this.packingPreference = packingPreference;
            this.pickupTime = pickupTime;
            this.dropoffTime = dropoffTime;
            this.serviceCost = serviceCost;
            this.paymentTime = paymentTime;
        }
    }

    private final Map<String, Booking> bookings = new HashMap<>();
    private int bookingIdCounter = 1;

    public void bookService(Scanner scanner, String role) {
        System.out.println("Enter Recipient Name:");
        String recName = scanner.nextLine();
        System.out.println("Enter Recipient Address:");
        String recAddress = scanner.nextLine();
        System.out.println("Enter Recipient Mobile:");
        String recMobile = scanner.nextLine();
        System.out.println("Enter Parcel Weight (grams):");
        String weight = scanner.nextLine();
        System.out.println("Enter Parcel Contents Description:");
        String contents = scanner.nextLine();
        System.out.println("Enter Delivery Type:");
        String deliveryType = scanner.nextLine();
        System.out.println("Enter Packing Preference:");
        String packingPreference = scanner.nextLine();
        System.out.println("Enter Pickup Time (YYYY-MM-DD):");
        Date pickupTime = parseDate(scanner.nextLine());
        System.out.println("Enter Dropoff Time (YYYY-MM-DD):");
        Date dropoffTime = parseDate(scanner.nextLine());

        double serviceCost = calculateServiceCost(weight, deliveryType);

        Booking booking = new Booking("BOOK" + bookingIdCounter++, "Current User", recName, recAddress, recMobile, weight, contents,
                deliveryType, packingPreference, pickupTime, dropoffTime, serviceCost, new Date());

        bookings.put(booking.bookingId, booking);
        System.out.println("Booking successful. Booking ID: " + booking.bookingId);
    }

    public void trackStatus(Scanner scanner, String role) {
        System.out.println("Enter Booking ID:");
        String bookingId = scanner.nextLine();
        Booking booking = bookings.get(bookingId);
        if (booking != null) {
            System.out.println("Booking ID: " + booking.bookingId);
            System.out.println("Recipient Name: " + booking.recName);
            System.out.println("Recipient Address: " + booking.recAddress);
            System.out.println("Recipient Mobile: " + booking.recMobile);
            System.out.println("Parcel Weight: " + booking.weight);
            System.out.println("Contents: " + booking.contents);
            System.out.println("Delivery Type: " + booking.deliveryType);
            System.out.println("Packing Preference: " + booking.packingPreference);
            System.out.println("Pickup Time: " + booking.pickupTime);
            System.out.println("Dropoff Time: " + booking.dropoffTime);
            System.out.println("Service Cost: " + booking.serviceCost);
            System.out.println("Payment Time: " + booking.paymentTime);
        } else {
            System.out.println("No booking found with ID " + bookingId);
        }
    }

    public void viewBookingHistory(Scanner scanner, String role) {
        System.out.println("Booking History:");
        for (Booking booking : bookings.values()) {
            System.out.println("Booking ID: " + booking.bookingId);
            System.out.println("Recipient Name: " + booking.recName);
            System.out.println("Recipient Address: " + booking.recAddress);
            System.out.println("Recipient Mobile: " + booking.recMobile);
            System.out.println("Parcel Weight: " + booking.weight);
            System.out.println("Contents: " + booking.contents);
            System.out.println("Delivery Type: " + booking.deliveryType);
            System.out.println("Packing Preference: " + booking.packingPreference);
            System.out.println("Pickup Time: " + booking.pickupTime);
            System.out.println("Dropoff Time: " + booking.dropoffTime);
            System.out.println("Service Cost: " + booking.serviceCost);
            System.out.println("Payment Time: " + booking.paymentTime);
            System.out.println("----------------------");
        }
    }

    public void updateDeliveryStatus(Scanner scanner) {
        System.out.println("Enter Booking ID:");
        String bookingId = scanner.nextLine();
        Booking booking = bookings.get(bookingId);
        if (booking != null) {
            System.out.println("Enter new delivery status:");
            String status = scanner.nextLine();
            System.out.println("Delivery status updated to: " + status);
        } else {
            System.out.println("No booking found with ID " + bookingId);
        }
    }

    public void updatePickupSchedule(Scanner scanner) {
        System.out.println("Enter Booking ID:");
        String bookingId = scanner.nextLine();
        Booking booking = bookings.get(bookingId);
        if (booking != null) {
            System.out.println("Enter new pickup time (YYYY-MM-DD):");
            Date newPickupTime = parseDate(scanner.nextLine());
            booking.pickupTime = newPickupTime;
            System.out.println("Pickup time updated to: " + newPickupTime);
        } else {
            System.out.println("No booking found with ID " + bookingId);
        }
    }

    private Date parseDate(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private double calculateServiceCost(String weight, String deliveryType) {
        // Example cost calculation
        return 10.0 + Double.parseDouble(weight) / 1000.0;
    }
}
