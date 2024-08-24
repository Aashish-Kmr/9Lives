package controller.person;

import entity.person.Doctor;
import storage.appointment.AppointmentStorage;
import storage.appointment.AppointmentStorageImpl;
import storage.person.DoctorStorage;
import storage.person.DoctorStorageImpl;

import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminController {
    public static Doctor registerDoctor(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter first-Name");
        String firstName = scanner.next();

        System.out.println("Enter Last-Name");
        String lastName = scanner.next();

        System.out.println("Enter Username");
        String username = scanner.next();

        String password;
        String re_password;

        while (true){
            System.out.println("Enter password");
            password = scanner.next();

            System.out.println("Re-Enter password");
            re_password = scanner.next();

            if(Objects.equals(password, re_password)){
                break;
            } else {
                System.out.println("Passwords don't match! \n Enter again");
            }
        }

        String email;
        boolean isvalid;
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailRegex);
        while (true){
            System.out.println("Enter e-mail");
            email = scanner.next();
            Matcher matcher = pattern.matcher(email);
            isvalid = matcher.matches();
            if (isvalid){
                break;
            } else{
                System.out.println("Invalid E-mail\nEnter again");
            }
        }

        System.out.println("Enter Role");
        String role = scanner.next();

        System.out.println("Enter Specialization");
        String specialization = scanner.next();

        DoctorStorage doctorStorage = DoctorStorageImpl.getDoctorStorage();
        int doctorId = doctorStorage.getNewId();

        scanner.close();
        return new Doctor(username,password,email,role,doctorId,firstName,lastName,specialization);
    }

    public static void updateDoctorSchedule(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Doctor's ID");
        int doctorId = scanner.nextInt();

        System.out.println("All appointments for id : "+doctorId);
        AppointmentStorage appointmentStorage = AppointmentStorageImpl.getAppointmentStorage();
        appointmentStorage.getAppointmentInfo(doctorId);

        boolean choiceStatus=true;
        while (choiceStatus){
            System.out.println("Select an appointment");
            int appointmentId = scanner.nextInt();

            System.out.println("Select your choice");
            System.out.println(" [1] -> Update an appointment");
            System.out.println(" [2] -> Delete an appointment");
            System.out.println(" [0] -> Exit");

            int choice = scanner.nextInt();
            switch (choice){
                case 1 : appointmentStorage.updateAppointment(appointmentId);
                break;

                case 2 : appointmentStorage.deleteAppointment(appointmentId);
                break;

                case 0: choiceStatus=false;
                break;

                default:
                    System.out.println("Invalid Choice! try again");
            }
        }
        System.out.println("Appointment/s modified");
        scanner.close();
    }

    public static void updateDoctorDetails(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Doctor Id");
        int doctorId = scanner.nextInt();
        System.out.println("Doctor Details");
        DoctorStorage doctorStorage = DoctorStorageImpl.getDoctorStorage();
        doctorStorage.getDoctorDetails(doctorId);

        boolean choiceStatus = true;

        while (choiceStatus){
            System.out.println("Select operations");
            System.out.println("[1] -> change First-name");
            System.out.println("[2] -> change Last-name");
            System.out.println("[3] -> change role");
            System.out.println("[4] -> change specialization");
            System.out.println("[0] -> exit");

            int choice = scanner.nextInt();

            switch (choice){
                case 1 :
                    System.out.println("Enter new firstName");
                    String firstName = scanner.next();
                    doctorStorage.updateFirstName(firstName);
                    break;

                case 2 :
                    System.out.println("Enter new lastName");
                    String lastName = scanner.next();
                    doctorStorage.updateLastName(lastName);
                    break;

                case 3 :
                    System.out.println("Enter new role");
                    String role = scanner.next();
                    doctorStorage.updateRole(role);
                    break;

                case 4 :
                    System.out.println("Enter new specialization");
                    String specialization = scanner.next();
                    doctorStorage.updateSpecialization(specialization);
                    break;

                case 0 : choiceStatus=false;
            }
        }
        System.out.println("Records updated Successfully");
        scanner.close();
    }

    public static void removeDoctor(){
        Scanner scanner = new Scanner(System.in);
        DoctorStorage doctorStorage = DoctorStorageImpl.getDoctorStorage();
        int doctorId;
        while (true){
            System.out.println("Enter Doctor Id");
            doctorId = scanner.nextInt();
            System.out.println("Doctor details");
            doctorStorage.getDoctorDetails(doctorId);
            System.out.println("Confirm your Choice");
            System.out.println("[1] -> Yes");
            System.out.println("[2] -> Select again");
            System.out.println("[0] -> exit");

            int confirmChoice = scanner.nextInt();
            if (confirmChoice==1 || confirmChoice==0){
                break;
            }
        }
        System.out.println("Permanently remove doctor details?");
        System.out.println("[1] -> Yes");
        System.out.println("[2] -> No");
        int choice = scanner.nextInt();
        if (choice==1){
            doctorStorage.removeDoctorDetails(doctorId);
            System.out.println("Details removed successfully");
        }
        else {
            System.out.println("Process terminated");
        }

    }
}









