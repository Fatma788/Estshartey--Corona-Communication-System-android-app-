package com.example.estshartey;

public class patient {
    public String Email;
    public int Gender;
    public String name;
    public String Password;

    public patient() {
    }

    public patient(String email, int gender, String name, String password, String birthday_date, String job) {
        Email = email;
        Gender = gender;
        this.name = name;
        Password = password;
        Birthday_date = birthday_date;
        Job = job;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        Gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getBirthday_date() {
        return Birthday_date;
    }

    public void setBirthday_date(String birthday_date) {
        Birthday_date = birthday_date;
    }

    public String getJob() {
        return Job;
    }

    public void setJob(String job) {
        Job = job;
    }

    public String Birthday_date;
    public String Job;
}
