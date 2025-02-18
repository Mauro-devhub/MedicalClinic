package UIMenu;

import Models.Doctor;
import UIMenu.Interface.IMenuOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class DoctorMenu implements IMenuOptions {
    private static final ItemMenu[] listDoctorMenuItems = new ItemMenu[]{
        new ItemMenu(1, "Add Available Appointment"),
        new ItemMenu(2, "My Scheduled appointments"),
        new ItemMenu(3, "Logout")
    };

    private static ArrayList<Doctor> doctorAppointments = new ArrayList<Doctor>();

    public static ArrayList<Doctor> getDoctorAppointments() {
        return doctorAppointments;
    }

    public static void showMenuDoctor() {
        int response;
        int[] listItemsOptions = {1,2,3};

        System.out.println("***********************************");
        System.out.println("Welcome Dr " + Menu.doctorLogged.getName());
        System.out.println("***********************************");

        response = Integer.parseInt(IMenuOptions.showUiMenuItems(listDoctorMenuItems));

        if (Arrays.stream(listItemsOptions).anyMatch(e -> e == response)) {
            ItemMenu itemSelected = IMenuOptions.getItemOptionById(listDoctorMenuItems, response);

            switch (response) {
                case 1:
                    System.out.println("Item selected: " + itemSelected.getItemName());
                    addAvailableAppointment();
                    break;

                case 3:
                    Menu.doctorLogged = null;
                    Menu.showUIMenu();
            }
        }
    }

    private static void getMoths() {
        int numberMonths = 3;
        for (int i = 0; i < numberMonths; i++) {
            System.out.println((i + 1) + " - " + Menu.MONTHS[i]);
        }
    }

    private static void addAvailableAppointment() {
        int response;

        getMoths();
        System.out.println("0 - Back to dr. " + Menu.doctorLogged.getName() + " menu \n");

        response = Integer.parseInt(IMenuOptions.catchUserInputValue());
        if (response == 0) {
            showMenuDoctor();
        } else {
            final String monthSelected = Menu.MONTHS[response - 1];
            System.out.println("You selected month: " + monthSelected);

            System.out.println("Insert the date available: [dd/mm/yyyy]");
            final String dateAvailable = IMenuOptions.catchUserInputValue();
            System.out.println("Your date selected month: " + monthSelected + "\ndate of the month: " + dateAvailable);

            System.out.println("Are you sure to schedule this date");
            System.out.println("1. Yes \n2. No");

            if (Integer.parseInt(IMenuOptions.catchUserInputValue()) == 2) {
                addAvailableAppointment();
            };

            String timeResponse;
            int optionResponse;

            do {
                System.out.println("Insert time available: [HH:MM]");
                timeResponse = IMenuOptions.catchUserInputValue();

                System.out.println("Are you sure to schedule this time: " + timeResponse);
                System.out.println("1. Yes \n2. No");

                optionResponse = Integer.parseInt(IMenuOptions.catchUserInputValue());
            } while (optionResponse == 2);

            Menu.doctorLogged.addAvailableAppointment(dateAvailable, timeResponse);
            checkDoctorAvailableAppointments();

            System.out.println(Menu.doctorLogged.toString());

            System.out.println("Dr. " + Menu.doctorLogged.getName() + " want schedule other appointment ?");
            System.out.println("1. Yes \n2.No");

            if (Integer.parseInt(IMenuOptions.catchUserInputValue()) != 2) {
                addAvailableAppointment();
            }

            DoctorMenu.showMenuDoctor();
        }
    }

    private static void checkDoctorAvailableAppointments(){
        ArrayList<Integer> doctorIds = doctorAppointments.stream().map((Doctor::getDoctorId)).collect(Collectors.toCollection(ArrayList::new));

        if (doctorIds.contains(Menu.doctorLogged.getDoctorId())){
            System.out.println("Doctor already exist");
        } else {
            doctorAppointments.add(Menu.doctorLogged);
            doctorAppointments.forEach((Doctor::toString));
        }
    }
}
