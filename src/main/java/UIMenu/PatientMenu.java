package UIMenu;

import Models.Doctor;
import UIMenu.Interface.IMenuOptions;

import java.util.*;

public class PatientMenu implements IMenuOptions {

    public static void showPatientMenu(){
        int response = 0;
        do {

            System.out.println("\n\n");
            System.out.println("Patient");
            System.out.println("Welcome: " + Menu.patientLogged);
            System.out.println("1. Book an appointment");
            System.out.println("2. My Appointments");
            System.out.println("0. Logout");

            Scanner sc = new Scanner(System.in);
            response = Integer.valueOf(sc.nextLine());

            switch (response){
                case 1:
                    showBookAppointmentMenu();
                    break;
                case 2:
                    break;
                case 0:
                    Menu.showUIMenu();
                    break;
            }


        }while (response!=0);
    }

    private static void showBookAppointmentMenu(){
        int response = 0;
        do {
            System.out.println("::Book an appointment");
            System.out.println(":: Select date: ");
            //Numeración de la lista de fechas
            //Indice fecha seleccionada
            //[doctors]
            // 1.- doctor1
            // - 1 fecha1
            // - 2 fecha2
            // 2.- doctor2
            // 3.- doctor3
            Map<Integer, Map<Integer, Doctor>> doctors = new TreeMap<>();
            int k = 0;
            for (int i = 0; i < DoctorMenu.getDoctorAppointments().size(); i++) {
                ArrayList<Doctor.AvailableAppointment> availableAppointments
                        = DoctorMenu.getDoctorAppointments().get(i).getAllAvailableAppointments();

                Map<Integer, Doctor>  doctorAppointments = new TreeMap<>();

                for (int j = 0; j < availableAppointments.size(); j++) {
                    k++;
                    System.out.println(k + ". " + availableAppointments.get(j).getDate());
                    doctorAppointments.put(j, DoctorMenu.getDoctorAppointments().get(i));

                    doctors.put(k, doctorAppointments);
                }
            }
            Scanner sc = new Scanner(System.in);
            int responseDateSelected = Integer.parseInt(IMenuOptions.catchUserInputValue());
            Map<Integer, Doctor> doctorAvailableSelected = doctors.get(responseDateSelected);
            Integer indexDate = 0;
            Doctor doctorSelected = new Doctor("","");

            for (Map.Entry<Integer, Doctor> doc :doctorAvailableSelected.entrySet()) {
                indexDate = doc.getKey();
                doctorSelected = doc.getValue();
            }

            System.out.println(doctorSelected.getName() +
                    ". Date: " +
                    doctorSelected.getAllAvailableAppointments().get(indexDate).getDate() +
                    ". Time: " +
                    doctorSelected.getAllAvailableAppointments().get(indexDate).getTime());

            System.out.println("Confirm your appointment: \n1. Yes \n2. Change Data");
            response = Integer.parseInt(IMenuOptions.catchUserInputValue());

            if (response == 1){
                Menu.patientLogged.addAppointmentDoctors(
                        doctorSelected,
                        doctorSelected.getAllAvailableAppointments().get(indexDate).getDate(null),
                        doctorSelected.getAllAvailableAppointments().get(indexDate).getTime());

                showPatientMenu();
            }


        }while (response!= 0);
    }

    private static void showPatientMyAppointments(){
        int response = 0;
        do {
            System.out.println("::My Appointments");
            if (Menu.patientLogged.getAppointmentDoctors().size() == 0){
                System.out.println("Don't have appointments");
                break;
            }

            for (int i = 0; i < Menu.patientLogged.getAppointmentDoctors().size(); i++) {
                int j = i + 1;
                System.out.println(j + ". " +
                        "Date: " + Menu.patientLogged.getAppointmentDoctors().get(i).getDate() +
                        " Time: " + Menu.patientLogged.getAppointmentDoctors().get(i).getTime() +
                        "\n Doctor: " + Menu.patientLogged.getAppointmentDoctors().get(i).getDoctor().getName()
                );
            }

            System.out.println("0. Return");
        }while (response!=0);
    }
}
