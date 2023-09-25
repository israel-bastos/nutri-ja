package br.com.grupo.nutrija.application.domain.user;

import br.com.grupo.nutrija.application.domain.Access;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class SystemAccessUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String fullName;

    private Access access;

    public SystemAccessUser(){}

    protected SystemAccessUser(String username, String password, String fullName, Access access) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.access = access;
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

    public Access getAccess() {return access;}

    public void setAccess(Access access) {this.access = access;}
}
