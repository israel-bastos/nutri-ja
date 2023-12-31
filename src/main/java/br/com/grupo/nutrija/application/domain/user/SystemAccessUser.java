package br.com.grupo.nutrija.application.domain.user;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@MappedSuperclass
public abstract class SystemAccessUser {

    private String username;

    private String password;

    private String fullName;

    private final LocalDateTime registryDate = LocalDateTime.now();

    private String displayImage;

    private String notificationEmail;

    private String phone;

    protected SystemAccessUser(){}

    protected SystemAccessUser(String username, String password, String fullName, String displayImage, String notificationEmail, String phone) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.displayImage = displayImage;
        this.notificationEmail = notificationEmail;
        this.phone = phone;
    }

    public String getUsername() {return username;}

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {return password;}

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {return fullName;}

    public void setFullName(String name) {
        this.fullName = name;
    }

    public String getRegistryDate() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(registryDate);
    }

    public String getDisplayImage() {
        return displayImage;
    }

    public void setDisplayImage(String displayImage) {
        this.displayImage = displayImage;
    }

    public String getNotificationEmail() {
        return notificationEmail;
    }

    public void setNotificationEmail(String email) {
            this.notificationEmail = email;
    }

    public String getPhone() {return phone;}

    public void setPhone(String phone) {this.phone = phone;}
}
