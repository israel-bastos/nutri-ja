package br.com.grupo.nutrija.application.domain.administrator;

import br.com.grupo.nutrija.application.domain.UserAccess;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public record AdministratorUserDetailsImpl(Administrator administrator) implements UserDetails {

    public Long getId() {
        return administrator.getId();
    }

    public String getFullName() {
        return administrator.getFullName();
    }

    public String displayImage(){
        return administrator.getDisplayImage();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String userAccess = administrator.userAccess;
        if(userAccess.equalsIgnoreCase(UserAccess.ADMIN.name())){
            userAccess = UserAccess.ADMIN.name();

        } else {
            userAccess = UserAccess.ADMIN.name();
        }

        return AuthorityUtils.createAuthorityList(userAccess);
    }

    @Override
    public String getPassword() {
        return administrator.getPassword();
    }

    @Override
    public String getUsername() {
        return administrator.getUsername();
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
