package by.it_academy.team1.usermessages.core.dto;

import java.time.LocalDate;

public class UserRegistrationDto {
    private String username;
    private String password;
    private String fullName;
    private LocalDate birthday;

    public UserRegistrationDto() {
    }

    public UserRegistrationDto(String username, String password, String fullName, LocalDate birthday) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.birthday = birthday;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "UserRegistrationDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
