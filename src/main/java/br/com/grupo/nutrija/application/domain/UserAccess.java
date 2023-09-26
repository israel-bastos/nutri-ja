package br.com.grupo.nutrija.application.domain;

public enum UserAccess {

    ADMIN("ADMIN"),

    PROFESSIONAL("PROFESSIONAL");

    private final String userAccess;

    UserAccess(String userAccess){
        this.userAccess = userAccess;
    }

    public String getUserAccess() {return userAccess;}

}
