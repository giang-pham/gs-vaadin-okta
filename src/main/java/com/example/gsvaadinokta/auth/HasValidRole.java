package com.example.gsvaadinokta.auth;

import com.example.gsvaadinokta.ui.applayout.RoleBaseView;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public interface HasValidRole {

    static boolean valid(Class<? extends RoleBaseView> view) {
        if (!view.isAnnotationPresent(ViewRole.class))
            return false;

        String role = view.getAnnotation(ViewRole.class).value();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(r -> r.equals(role));
    }
}
