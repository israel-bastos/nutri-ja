package br.com.grupo.nutrija.application.domain.user.entity;

public enum Perfils {

    ADMIN(1), USER(2);

    private final int value;

    Perfils(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
