package presentation;

import controller.person.AdminController;
import entity.person.Admin;
import entity.person.Person;
import storage.person.AdminStorage;
import storage.person.AdminStorageImpl;

import java.util.Scanner;

public class AdminUI {

    public static void run(Person person) {
        AdminStorage adminStorage = AdminStorageImpl.getAdminStorage();
        Admin admin = adminStorage.getAdmin(person.getUsername());

        System.out.println("Welcome " + admin.getName());
        System.out.println("Admin Details : ");
        admin.toString();

        boolean choiceStatus = true;
        Scanner scanner = new Scanner(System.in);
        while (choiceStatus){
            System.out.println("Select the operations");
            System.out.println("[1] -> Register Doctor");
            System.out.println("[2] -> View/Update/Cancel Doctor's schedule");
            System.out.println("[3] -> Update doctor details");
            System.out.println("[4] -> Remove doctor Details");
            System.out.println("[0] -> Exit");

            int choice = scanner.nextInt();
            switch (choice){
                case 1 : AdminController.registerDoctor();
                break;

                case 2 : AdminController.updateDoctorSchedule();
                break;

                case 3 : AdminController.updateDoctorDetails();
                break;

                case 4 : AdminController.removeDoctor();
                break;

                case 0 : choiceStatus=false;
                break;

                default:
                    System.out.println("Invalid Input! Try Again");
            }
        }
        scanner.close();
    }

}
