package br.com.grupo.nutrija.application.domain.user;

import br.com.grupo.nutrija.application.domain.UserAccess;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@MappedSuperclass
public abstract class SystemAccessUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String fullName;

    private LocalDateTime registryDate = LocalDateTime.now();

    private String userAccess;

    protected SystemAccessUser(){}

    protected SystemAccessUser(String username, String password, String fullName, UserAccess userAccess) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.userAccess = userAccess.name();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String name) {
        this.fullName = name;
    }

    public String getRegistryDate() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(registryDate);
    }

    public String getAccess() {return userAccess;}

    public void setAccess(String userAccess) {this.userAccess = userAccess;}
}
