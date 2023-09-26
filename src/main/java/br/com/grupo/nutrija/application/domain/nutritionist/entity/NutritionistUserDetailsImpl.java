package br.com.grupo.nutrija.application.domain.nutritionist.entity;

import br.com.grupo.nutrija.application.domain.UserAccess;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public record NutritionistUserDetailsImpl(Nutritionist nutritionist) implements UserDetails {

    public Long getId() {
        return nutritionist.getId();
    }

    public String getFullName() {
        return nutritionist.getFullName();
    }

    public String displayImage(){
        return nutritionist.getDisplayImage();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String userAccess = nutritionist.userAccess;
        if(userAccess.equalsIgnoreCase(UserAccess.PROFESSIONAL.name())){
            userAccess = UserAccess.PROFESSIONAL.name();

        } else {
            userAccess = UserAccess.PROFESSIONAL.name();
        }

        return AuthorityUtils.createAuthorityList(userAccess);
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
}
