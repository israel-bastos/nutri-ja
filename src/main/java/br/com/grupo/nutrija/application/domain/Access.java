package br.com.grupo.nutrija.application.domain;

public enum Access {

    ADMIN("Administrator"),

    PROFESSIONAL("Professional");

    private final String access;

    Access(String access){
        this.access = access;
    }

    public String getAcesso() {return access;}

}
