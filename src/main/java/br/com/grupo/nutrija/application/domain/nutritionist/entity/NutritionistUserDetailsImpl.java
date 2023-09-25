package br.com.grupo.nutrija.application.domain.nutritionist.entity;

import br.com.grupo.nutrija.application.domain.Access;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public record NutritionistUserDetailsImpl(Nutritionist nutritionist) implements UserDetails {

    public Long getId() {
        return nutritionist.getId();
    }

    public String getFullName() {
        return nutritionist.getFullName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Access access = nutritionist.getAccess();
        if(access == Access.ADMIN){
            access = Access.ADMIN;

        } else {
            access = Access.PROFESSIONAL;
        }

        return AuthorityUtils.createAuthorityList(access.toString());
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
