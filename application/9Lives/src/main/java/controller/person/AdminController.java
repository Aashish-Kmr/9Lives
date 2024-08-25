package controller.person;

import entity.person.Doctor;
import entity.person.Person;
import storage.appointment.AppointmentStorage;
import storage.person.DoctorStorage;
import storage.person.PersonStorage;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminController {

    private static final DoctorStorage doctorStorage;
    private static final PersonStorage personStorage;
    private static final AppointmentStorage appointmentStorage;

    static {
        doctorStorage = DoctorStorage.getInstance();
        personStorage = PersonStorage.getInstance();
        appointmentStorage = AppointmentStorage.getInstance();
    }

    public static void registerDoctor() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter first-Name");
        String firstName = scanner.nextLine();

        System.out.println("Enter Last-Name");
        String lastName = scanner.nextLine();

        System.out.println("Enter Username");
        String username = scanner.nextLine();

        String password;
        String re_password;

        while (true) {
            System.out.println("Enter password");
            password = scanner.next();

            System.out.println("Re-Enter password");
            re_password = scanner.next();

            if (Objects.equals(password, re_password)) {
                break;
            } else {
                System.out.println("Passwords don't match! \n Enter again");
            }
        }

        String email;
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailRegex);
        while (true) {
            System.out.println("Enter e-mail");
            email = scanner.nextLine();
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                break;
            } else {
                System.out.println("Invalid E-mail\nEnter again");
            }
        }

        System.out.println("Enter Specialization");
        String specialization = scanner.nextLine();

        int doctorId = doctorStorage.getNextId();

        Person person = new Person(username, password, email, "doctor");
        personStorage.addPerson(person);
        Doctor doctor = new Doctor(doctorId, firstName, lastName, specialization, username);
        doctorStorage.addDoctor(doctor);

        scanner.close();
    }

    public static void updateDoctorSchedule() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        doctorStorage.showAllDoctors();
        System.out.println("Enter Doctor's ID");
        int doctorId = Integer.parseInt(scanner.nextLine());

        boolean choiceStatus = true;
        while (choiceStatus) {
            appointmentStorage.showAppointments(doctorId);
            System.out.println("Select an appointment");
            int appointmentId = Integer.parseInt(scanner.nextLine());

            System.out.println("Select your choice");
            System.out.println(" [1] -> Cancel an appointment");
            System.out.println(" [2] -> Delete an appointment");
            System.out.println(" [0] -> Exit");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    appointmentStorage.cancelAppointment(appointmentId);
                    break;

                case 2:
                    appointmentStorage.deleteAppointment(appointmentId);
                    break;

                case 0:
                    choiceStatus = false;
                    break;

                default:
                    System.out.println("Invalid Choice! try again");
            }
        }
        System.out.println("Appointment/s modified");
        scanner.close();
    }

    public static void updateDoctorDetails() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        doctorStorage.showAllDoctors();
        System.out.println("Enter Doctor Id");
        int doctorId = Integer.parseInt(scanner.nextLine());
        System.out.println("Doctor current Details");
        doctorStorage.showDoctorDetails(doctorId);

        boolean choiceStatus = true;

        while (choiceStatus) {
            System.out.println("Select operations");
            System.out.println("[1] -> change First-name");
            System.out.println("[2] -> change Last-name");
            System.out.println("[3] -> change specialization");
            System.out.println("[0] -> exit");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("Enter new firstName");
                    String firstName = scanner.nextLine();
                    doctorStorage.updateFirstName(doctorId, firstName);
                    break;

                case 2:
                    System.out.println("Enter new lastName");
                    String lastName = scanner.nextLine();
                    doctorStorage.updateLastName(doctorId, lastName);
                    break;

                case 3:
                    System.out.println("Enter new specialization");
                    String specialization = scanner.nextLine();
                    doctorStorage.updateSpecialization(doctorId, specialization);
                    break;

                case 0:
                    choiceStatus = false;
            }
        }
        System.out.println("Records updated Successfully");
        scanner.close();
    }

    public static void removeDoctor() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int doctorId;
        while (true) {
            doctorStorage.showAllDoctors();
            System.out.println("Enter Doctor Id");
            doctorId = Integer.parseInt(scanner.nextLine());
            System.out.println("Doctor selected");
            doctorStorage.showDoctorDetails(doctorId);
            System.out.println("Confirm your Choice");
            System.out.println("[1] -> Yes");
            System.out.println("[2] -> Select again");
            System.out.println("[0] -> exit");

            int confirmChoice = Integer.parseInt(scanner.nextLine());
            if (confirmChoice == 1 || confirmChoice == 0) {
                break;
            }
        }
        System.out.println("Permanently remove doctor details?");
        System.out.println("[1] -> Yes");
        System.out.println("[2] -> No");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 1) {
            doctorStorage.removeDoctor(doctorId);
            System.out.println("Details removed successfully");
        } else {
            System.out.println("Process terminated");
        }

    }
}









