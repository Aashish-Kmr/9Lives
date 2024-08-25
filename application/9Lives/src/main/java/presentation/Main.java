package presentation;

import entity.person.Person;
import storage.person.PersonStorage;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to 9Lives Application");

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter your username: ");
            String username = scanner.nextLine();
            System.out.println("Enter you password: ");
            String password = scanner.nextLine();

            Person person = authenticateAndGetPerson(username, password);

            if (person != null) {
                switch (person.getRole()) {
                    case "admin":
                        AdminUI.run(person);
                        break;
                    case "doctor":
                        DoctorUI.run(person);
                        break;
                    case "user":
                        UserUI.run(person);
                        break;
                    default:
                        System.out.println("Role does not exist!");
                        break;
                }
            } else {
                System.out.println("Invalid username or password!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Person authenticateAndGetPerson(String username, String password) throws SQLException {
        PersonStorage personStorage = PersonStorage.getInstance();
        try {
            Person person = personStorage.getPerson(username);
            if (password.equals(person.getPassword()))
                return person;
            else
                System.out.println("Invalid username or password!");
        } catch (SQLException e) {
            System.out.println("Invalid username or password!");
        }
        return null;
    }
}
