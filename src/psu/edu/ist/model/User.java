package psu.edu.ist.model;

public class User {
    private String Name;
    private String Email;
    private String Phone;
    private Access AccessLevel;

    public User(String name, String email, String phone, Access accessLevel) {
        Name = name;
        Email = email;
        Phone = phone;
        AccessLevel = accessLevel;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public Access getAccessLevel() {
        return AccessLevel;
    }

    public void setAccessLevel(Access accessLevel) {
        AccessLevel = accessLevel;
    }

    @Override
    public String toString() {
        return "User{" +
                "Name='" + Name + '\'' +
                ", Email='" + Email + '\'' +
                ", Phone=" + Phone +
                ", AccessLevel=" + AccessLevel +
                '}';
    }
}

