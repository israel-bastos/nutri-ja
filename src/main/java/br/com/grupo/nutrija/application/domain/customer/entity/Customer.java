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

    private int age;

    private int weight;

    private double height;

    private double imc;

    private String phone;

    @ManyToOne
    private Nutritionist nutritionist;

    public Customer() {}

    public Customer(String fullName, LocalDateTime registryDate, String notificationEmail, Nutritionist nutritionist,
                    int age, int weight, double imc, double height, String phone) {

        this.fullName = fullName;
        this.registryDate = registryDate;
        this.notificationEmail = notificationEmail;
        this.nutritionist = nutritionist;
        this.age = age;
        this.weight = weight;
        this.imc = imc;
        this.height = height;
        this.phone = phone;
    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age=age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight=weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height=height;
    }

    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc=imc;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String cellPhone) {
        this.phone =cellPhone;
    }
}
