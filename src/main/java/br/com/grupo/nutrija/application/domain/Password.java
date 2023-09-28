package br.com.grupo.nutrija.application.domain;

public class Password {

    private String actualPassword;

    private String newPassword;

    public Password(){}

    public Password(String actualPassword){
        this.actualPassword = actualPassword;
    }

    public String getActualPassword() {
        return actualPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setActualPassword(String actualPassword) {
        this.actualPassword = actualPassword;
    }
}
