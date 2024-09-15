import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserService {
    private static class User {
        String name, email, address, userId, password, preferences, role;
        String mobileNumber;

        User(String name, String email, String address, String mobileNumber, String userId, String password, String preferences, String role) {
            this.name = name;
            this.email = email;
            this.address = address;
            this.mobileNumber = mobileNumber;
            this.userId = userId;
            this.password = password;
            this.preferences = preferences;
            this.role = role;
        }
    }

    private final Map<String, User> users = new HashMap<>();

    public void register(Scanner scanner) {
        System.out.println("Enter Customer Name (max 50 characters):");
        String name = scanner.nextLine();
        System.out.println("Enter Email:");
        String email = scanner.nextLine();
        System.out.println("Enter Mobile Number (10 digits):");
        String mobileNumber = scanner.nextLine();
        System.out.println("Enter Address:");
        String address = scanner.nextLine();
        System.out.println("Enter User ID (5-20 characters):");
        String userId = scanner.nextLine();
        System.out.println("Enter Password (max 30 characters):");
        String password = scanner.nextLine();
        System.out.println("Confirm Password:");
        String confirmPassword = scanner.nextLine();
        System.out.println("Enter Preferences:");
        String preferences = scanner.nextLine();
        System.out.println("Enter Role (Customer/Officer):");
        String role = scanner.nextLine();

        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match.");
            return;
        }

        User user = new User(name, email, address, mobileNumber, userId, password, preferences, role);
        users.put(userId, user);
        System.out.println("Registration successful.");
    }

    public String login(Scanner scanner) {
        System.out.println("Enter User ID:");
        String userId = scanner.nextLine();
        System.out.println("Enter Password:");
        String password = scanner.nextLine();

        User user = users.get(userId);
        if (user != null && user.password.equals(password)) {
            return user.role;
        }
        return null;
    }
}
