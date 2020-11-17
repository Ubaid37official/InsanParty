package rehanfoundation.app.insanparty.model;

public class User {
    private int id,user_id;
    private String name, email,phone, gender,dob,education,profession,location;

    public User(int id, int user_id, String name, String email, String phone, String gender, String dob, String education, String profession, String location){
        this.id = id;
        this.user_id =user_id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.dob = dob;
        this.education = education;
        this.profession = profession;
        this.location = location;
    }


    public int getId() {
        return id;
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
