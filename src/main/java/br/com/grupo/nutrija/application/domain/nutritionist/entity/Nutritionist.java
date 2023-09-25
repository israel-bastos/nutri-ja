package br.com.grupo.nutrija.application.domain.nutritionist.entity;

import br.com.grupo.nutrija.application.domain.UserAccess;
import br.com.grupo.nutrija.application.domain.user.SystemAccessUser;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Nutritionist extends SystemAccessUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Nutritionist() {}

    public Nutritionist(String username, String password, String fullName, UserAccess userAccess) {
        super(username, password, fullName, userAccess);
    }
}
