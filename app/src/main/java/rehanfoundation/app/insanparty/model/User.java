package rehanfoundation.app.insanparty.model;

public class User {
    private boolean status;
    private String message;
    private int user_id;
    private String name, email,phone, gender,dob,education,profession,location;

    public User(boolean status, String message, int user_id, String name, String email, String phone, String gender, String dob, String education, String profession, String location) {
        this.status = status;
        this.message = message;
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.dob = dob;
        this.education = education;
        this.profession = profession;
        this.location = location;
    }

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public String getDob() {
        return dob;
    }

    public String getEducation() {
        return education;
    }

    public String getProfession() {
        return profession;
    }

    public String getLocation() {
        return location;
    }
}
