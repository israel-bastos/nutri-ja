package br.com.grupo.nutrija.application.domain.administrator;

import br.com.grupo.nutrija.application.domain.UserAccess;
import br.com.grupo.nutrija.application.domain.user.SystemAccessUser;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Administrator extends SystemAccessUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public final String userAccess = UserAccess.ADMIN.name();

    public Administrator() {}

    public Administrator(String username, String password, String fullName, String displayImage) {
        super(username, password, fullName, displayImage);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
