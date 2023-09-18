package br.com.grupo.nutrija.application.domain.professional.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class NutritionistUserDetailsImpl implements UserDetails {

    private Nutritionist nutritionist;

    public NutritionistUserDetailsImpl(Nutritionist nutritionist){
        this.nutritionist = nutritionist;
    }

    public Long getId(){
        return nutritionist.getId();
    }

    public String getName(){
        return nutritionist.getName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Perfil perfil = nutritionist.getPerfil();
//        if(perfil == Perfil.ADMIN){
//            perfil = Perfil.ADMIN;
//        }else{
//            perfil = Perfil.TECNICO;
//        }
//        return AuthorityUtils.createAuthorityList(perfil.toString());

        return null;
    }

    @Override
    public String getPassword() {
        return nutritionist.getPassword();
    }

    @Override
    public String getUsername() {
        return nutritionist.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Nutritionist getNutritionist() {
        return nutritionist;
    }
}
