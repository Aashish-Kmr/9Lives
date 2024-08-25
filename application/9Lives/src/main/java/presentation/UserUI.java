package presentation;

import controller.person.UserController;
import entity.person.Person;
import entity.person.User;
import storage.person.UserStorage;

import java.sql.SQLException;
import java.util.Scanner;

public class UserUI {

    private static final UserStorage userStorage = UserStorage.getInstance();

    public static void run(Person person) throws SQLException {
        User user;
        try {
            user = userStorage.getUser(person);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int choice = -1;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("What would you like to do?");
            System.out.println("[1] Register new patient");
            System.out.println("[2] Book appointment for an existing patient");
            System.out.println("[3] Exit");
            System.out.print("Your choice: ");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    UserController.registerNewPatient(user.getUserId(), scanner);
                    break;
                case 2:
                    UserController.bookAppointment(scanner);
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid choice, try again!");
            }
        } while (choice != 3);
        scanner.close();
    }
}
