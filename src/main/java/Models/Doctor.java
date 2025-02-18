package Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

public class Doctor extends User {
    private String specially;
    private final static ArrayList<AvailableAppointment> availableAppointments = new ArrayList<AvailableAppointment>();

    public Doctor(String name, String email) {
        super(name, email);
    }

    @Override
    public void showDataUser() {
        System.out.println("Employable del Hospital: Cruz Rojas");
        System.out.println("Department: Numerological");
    }

    public int getDoctorId() {
        return User.getId();
    }

    public String getSpecially() {
        return specially;
    }

    public void setSpecially(String specially) {
        this.specially = specially;
    }

    public void addAvailableAppointment(String date, String time) {
        availableAppointments.add(new AvailableAppointment(date, time));
    }

    public ArrayList<AvailableAppointment> getAllAvailableAppointments() {
        return availableAppointments;
    }

    @Override
    public String toString() {
        return super.toString() + "Doctor {" +
            "\n specially=" + specially +
            "\n availableAppointments=[\n  " +
                availableAppointments.stream()
                    .map(AvailableAppointment::toString)
                    .collect(Collectors.joining())
                +
            "] \n";
    }

    public static class AvailableAppointment {
        private String time;
        private Date date;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        public AvailableAppointment(String date, String time) {
            try {
                this.date = format.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            this.time = time;
        };

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public Date getDate(String DATE) {
            return date;
        }

        public String getDate() {
            return format.format(date);
        }

        public void setDate(Date date) {
            this.date = date;
        }

        @Override
        public String toString() {
            return " AvailableAppointment {" +
                    "\n    date=" + getDate(date.toString()) +
                    "\n    time=" + time +
                    "\n   } \n  ";
        }
    }
}
