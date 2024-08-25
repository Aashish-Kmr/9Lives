package controller;

import entity.appointment.Appointment;
import entity.medical.records.MedicalRecord;
import entity.medical.records.TestRecord;
import storage.appointment.AppointmentStorage;
import storage.medical.item.MedicalItemStorage;
import storage.medical.record.RecordStorage;

import java.sql.SQLException;
import java.util.Scanner;

public class DoctorController {

    private static final AppointmentStorage appointmentStorage;
    private static final MedicalItemStorage medicineStorage;
    private static final MedicalItemStorage medicalTestStorage;
    private static final RecordStorage medicalRecordStorage;
    private static final RecordStorage testRecordStorage;

    static {
        appointmentStorage = AppointmentStorage.getInstance();
        medicineStorage = MedicalItemStorage.getMedicalTestInstance();
        medicalTestStorage = MedicalItemStorage.getMedicalTestInstance();
        medicalRecordStorage = RecordStorage.getMedicalRecordInstance();
        testRecordStorage = RecordStorage.getTestRecordInstance();
    }

    public static void setSchedule(int doctorId) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Patient ID:");
        int patientId = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter Date and Time (YYYY-MM-DD HH:MM):");
        String datetime = scanner.nextLine();

        int appointmentId = appointmentStorage.getNextId();
        Appointment appointment = new Appointment(Appointment.Status.BOOKED, appointmentId, patientId, doctorId, datetime);
        appointmentStorage.addAppointment(appointment);

        System.out.println("Schedule added successfully.");
    }

    public static void viewSchedule(int doctorId) throws SQLException {
        System.out.println("Your appointments:");
        appointmentStorage.showAppointments(doctorId);
    }

    public static void updateSchedule(int doctorId) throws SQLException {
        Scanner scanner = new Scanner(System.in);

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

    public static void generateMedicalRecord(int doctorId) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        viewSchedule(doctorId);
        System.out.println("Enter the appointment id: ");
        int appointment_id = Integer.parseInt(scanner.nextLine());

        Appointment appointment = appointmentStorage.getAppointment(appointment_id);
        int patientId = appointment.getPatientId();

        medicineStorage.showAllItems();
        System.out.print("Enter medicine id: ");
        int medicineId = Integer.parseInt(scanner.nextLine());

        int recordId = medicalRecordStorage.getNextId();

        MedicalRecord medicalRecord = new MedicalRecord(recordId, patientId, doctorId, medicineId);
        medicalRecordStorage.addRecord(medicalRecord);

        appointmentStorage.deleteAppointment(appointment_id);
    }

    public static void generateTestRecord(int doctorId) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        viewSchedule(doctorId);
        System.out.println("Enter the appointment id: ");
        int appointment_id = Integer.parseInt(scanner.nextLine());

        Appointment appointment = appointmentStorage.getAppointment(appointment_id);
        int patientId = appointment.getPatientId();

        medicalTestStorage.showAllItems();
        System.out.print("Enter medicine id: ");
        int testId = Integer.parseInt(scanner.nextLine());

        int recordId = testRecordStorage.getNextId();

        TestRecord testRecord = new TestRecord(recordId, patientId, doctorId, testId);
        testRecordStorage.addRecord(testRecord);

        appointmentStorage.deleteAppointment(appointment_id);
    }
}
