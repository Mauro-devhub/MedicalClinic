package UIMenu;
import Models.Doctor;
import Models.Patient;
import Models.User;
import UIMenu.Interface.IMenuOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Menu implements IMenuOptions {
    private static final ItemMenu[] listMainMenuItems = new ItemMenu[]{
        new ItemMenu(1, "Doctor"),
        new ItemMenu(2, "Patient"),
        new ItemMenu(3, "Exit")
    };

    private static final ItemMenu[] listPatientMenuItems = new ItemMenu[]{
        new ItemMenu(1, "Book an appointment"),
        new ItemMenu(2, "My appointment"),
        new ItemMenu(3, "Back to main menu")
    };

    public static final String[] MONTHS = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    public static Doctor doctorLogged;
    public static Patient patientLogged;

    public static void showUIMenu() {
        int response;
        int finalResponse;
        int[] listItemsOptions = {1,2,3};

        System.out.println("***********************************");
        System.out.println("Select an option to log in");
        System.out.println("***********************************");

        response = Integer.parseInt(IMenuOptions.showUiMenuItems(listMainMenuItems));
        finalResponse = response;

        if (Arrays.stream(listItemsOptions).anyMatch(e -> e == finalResponse)) {

            ItemMenu itemSelected = IMenuOptions.getItemOptionById(listMainMenuItems, response);

            switch (response) {
                case 1, 2:
                    System.out.println("You selected option: " + itemSelected.itemName);
                    AuthUser(response);
                    break;
                default:
                    System.out.println("You exit of the program");
            }
        } else {
            System.out.println("Please choice a valid option");
            System.out.println("\n");
            showUIMenu();
        }
    }

    private static void AuthUser(int userType) {
        ArrayList<Doctor> doctors = new ArrayList<Doctor>();
        ArrayList<Patient> patients = new ArrayList<Patient>();

        boolean emailCorrect = false;

        //doctor
        Doctor doctor = new Doctor("Mauricio", "dsmauricio16@gmail.com");
        doctor.setSpecially("Circulant plástic");

        doctors.add(doctor);

        //patient
        Patient patient = new Patient("carlos", "carlos@gmail.com");
        patient.setHeight(6.0);
        patient.setWeight(180.5);
        patient.setBlood("o+");
        patient.setPhoneNumber("809-123-1234");

        patients.add(patient);

        if (!emailCorrect) {
            System.out.println("Introduce your email: ");
            String email = IMenuOptions.catchUserInputValue();

            if (userType == 1) {
                ArrayList<String> emails = doctors.stream().map(User::getEmail).collect(Collectors.toCollection(ArrayList::new));
                doctorLogged = doctors.stream().filter(e -> Objects.equals(e.getEmail(), email)).findFirst().orElse(null);

                emailCorrect = emails.contains(email);
                System.out.println("Email doctor exist: " + emailCorrect);
                DoctorMenu.showMenuDoctor();
            }

            if (userType == 2) {
                ArrayList<String> emails = patients.stream().map(User::getEmail).collect(Collectors.toCollection(ArrayList::new));
                patientLogged = patients.stream().filter(e -> Objects.equals(e.getEmail(), email)).findFirst().orElse(null);

                emailCorrect = emails.contains(email);
                System.out.println("Email patient exist: " + emailCorrect);
                PatientMenu.showPatientMenu();
            }
        }
    }
}
