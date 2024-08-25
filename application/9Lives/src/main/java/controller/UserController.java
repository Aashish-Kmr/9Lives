package controller;

import entity.appointment.Appointment;
import entity.person.Patient;
import storage.appointment.AppointmentStorage;
import storage.person.DoctorStorage;
import storage.person.PatientStorage;

import java.sql.SQLException;
import java.util.Scanner;

public class UserController {

    private static final PatientStorage patientStorage;
    private static final AppointmentStorage appointmentStorage;
    private static final DoctorStorage doctorStorage;

    static {
        patientStorage = PatientStorage.getInstance();
        appointmentStorage = AppointmentStorage.getInstance();
        doctorStorage = DoctorStorage.getInstance();
    }

    public static void registerNewPatient(int userId, Scanner scanner) throws SQLException {
        int id = patientStorage.getNextId();
        System.out.print("Enter first name: ");
        String firstname = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastname = scanner.nextLine();
        System.out.print("Enter medical condition: ");
        String medicalCondition = scanner.nextLine();

        Patient patient = new Patient(id, userId, firstname, lastname, medicalCondition);
        patientStorage.addPatient(patient);
    }

    public static void bookAppointment(Scanner scanner) throws SQLException {
        patientStorage.showAllPatients();
        System.out.print("Enter Patient id: ");
        int patient_id = Integer.parseInt(scanner.nextLine());

        if (patientStorage.checkPatientId(patient_id)) {
            doctorStorage.showAllDoctors();
            System.out.print("Enter doctor id: ");
            int doctor_id = Integer.parseInt(scanner.nextLine());

            appointmentStorage.showAppointments(doctor_id);
            System.out.print("Enter appointment date & time: ");
            String datetime = scanner.nextLine();

            int id = appointmentStorage.getNextId();

            Appointment appointment = new Appointment(
                    Appointment.Status.BOOKED,
                    id,
                    patient_id,
                    doctor_id,
                    datetime
            );
            appointmentStorage.addAppointment(appointment);
        }
    }
}
