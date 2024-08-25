package controller.person;

import entity.appointment.Appointment;
import storage.appointment.AppointmentStorage;
import storage.person.DoctorStorage;
import storage.person.PersonStorage;

import java.sql.SQLException;
import java.util.Scanner;

public class DoctorController {
    private static final AppointmentStorage appointmentStorage;
    private static final DoctorStorage doctorStorage;
    static {
        doctorStorage = DoctorStorage.getInstance();
        appointmentStorage = AppointmentStorage.getInstance();
    }
    public static void setSchedule() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        doctorStorage.showAllDoctors();
        System.out.println("Enter your Doctor ID:");
        int doctorId = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter Patient ID:");
        int patientId = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter Date and Time (YYYY-MM-DD HH:MM):");
        String datetime = scanner.nextLine();

        int appointmentId = appointmentStorage.getNextId();
        Appointment appointment = new Appointment(Appointment.Status.BOOKED, appointmentId, patientId, doctorId, datetime);
        appointmentStorage.addAppointment(appointment);

        System.out.println("Schedule added successfully.");
    }
    public static void updateDoctorSchedule() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your ID");
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
}
