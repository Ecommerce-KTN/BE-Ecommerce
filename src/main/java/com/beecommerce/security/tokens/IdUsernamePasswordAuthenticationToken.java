package com.beecommerce.security.tokens;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.Collection;

public class IdUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {
    @Getter
    @Setter
    private String userId;

    public IdUsernamePasswordAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, String userId) {
        super(principal, credentials, authorities);
        this.userId = userId;
    }

    @Override
    public boolean implies(Subject subject) {
        return super.implies(subject);
    }
}
