package Models;

public abstract class User {
    private static int id = 0;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;

    public User(String name, String email) {
        id++;
        this.name = name;
        this.email = email;
    }

    public static int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public abstract void showDataUser();

    @Override
    public String toString() {
        return "User {" +
                "\n id=" + id +
                "\n name=" + name +
                "\n email=" + email +
                "\n address=" + address +
                "\n phoneNumber=" + phoneNumber +
                "\n } \n\n";
    }
}
