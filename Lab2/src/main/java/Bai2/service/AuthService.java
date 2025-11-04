package Bai2.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service("auth")
public class AuthService{
    public Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
    public String getUsername(){
        return getAuthentication().getName();
    }
    public String getRoles(){
        return getAuthentication().getAuthorities().iterator().next().getAuthority();
    }
    public boolean isAuthenticated(){
        String Username = this.getUsername();
        return (Username!=null && !Username.equals("anonymousUser"));
    }
    public boolean hasAnyRole(String... rolesToCheck){
        var grantedRoles = this.getRoles();
        return Stream.of(rolesToCheck).anyMatch(role -> grantedRoles.contains(role));
    }
}
