package br.com.grupo.nutrija.application.domain.customer.entity;

import br.com.grupo.nutrija.application.domain.nutritionist.entity.Nutritionist;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;

    private LocalDateTime registryDate = LocalDateTime.now();

    private String notificationEmail;

    @ManyToOne
    private Nutritionist nutritionist;

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRegistryDate() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(registryDate);
    }

    public String getNotificationEmail() {
        return notificationEmail;
    }

    public void setNotificationEmail(String notificationEmail) {
        this.notificationEmail = notificationEmail;
    }

    public Nutritionist getNutritionist() {
        return nutritionist;
    }

    public void setNutritionist(Nutritionist nutritionist) {
        this.nutritionist = nutritionist;
    }
}
