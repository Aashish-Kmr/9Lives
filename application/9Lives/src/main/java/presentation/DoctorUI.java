package presentation;

import controller.DoctorController;
import entity.person.Doctor;
import entity.person.Person;
import storage.person.DoctorStorage;
import storage.person.DoctorStorageImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class DoctorUI {

    public static void run(Person person) throws SQLException {
        DoctorStorage doctorStorage = DoctorStorageImpl.getInstance();
        Doctor doctor = doctorStorage.getDoctor(person);
        System.out.println("Doctor Details: ");
        doctor.toString();

        boolean choiceStatus = true;
        Scanner scanner = new Scanner(System.in);

        while (choiceStatus) {
            System.out.println("Select the operations");
            System.out.println("[1] -> Set Schedule");
            System.out.println("[2] -> View Schedule");
            System.out.println("[3] -> Update Schedule");
            System.out.println("[4] -> Generate Medical record");
            System.out.println("[5] -> Generate Test Record");
            System.out.println("[0] -> Exit");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    DoctorController.setSchedule(doctor.getDoctorId());
                    break;

                case 2:
                    DoctorController.viewSchedule(doctor.getDoctorId());
                    break;

                case 3:
                    DoctorController.updateSchedule(doctor.getDoctorId());
                    break;

                case 4:
                    DoctorController.generateMedicalRecord(doctor.getDoctorId());
                    break;

                case 5:
                    DoctorController.generateTestRecord(doctor.getDoctorId());
                    break;

                case 0:
                    choiceStatus = false;
                    break;

                default:
                    System.out.println("Invalid Input! Try Again");
            }
        }
    }
}
