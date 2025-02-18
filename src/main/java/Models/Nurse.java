package Models;

public class Nurse extends User {
    private String specially;

    public Nurse(String name, String email) {
        super(name, email);
    }

    @Override
    public void showDataUser() {
        System.out.println("Employable del Hospital: Cruz Verde");
        System.out.println("Departments: Nutritional, Pediatric");
    }

    public String getSpecially() {
        return specially;
    }

    public void setSpecially(String specially) {
        this.specially = specially;
    }
}
