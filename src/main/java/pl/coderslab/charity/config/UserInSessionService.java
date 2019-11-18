package pl.coderslab.charity.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class UserInSessionService {
    private String loggedUserName;


    public String getUserFromSessionByLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public Collection<? extends GrantedAuthority> getAuthoriesFromSession() {

        Collection<? extends GrantedAuthority> authories = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return authories;
    }
}
