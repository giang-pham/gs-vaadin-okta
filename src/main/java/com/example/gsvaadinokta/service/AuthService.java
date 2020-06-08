/*--[UZABASE Asia Pacific Ltd.]--------------------------------m(._.)m--------*\
 |
 |  ::: crud-with-vaadin :::
 |
 |  Copyright (c) 2020 Uzabase Inc. all rights reserved.
 |
 |  Author: Asia PDT (asia-pdt@uzabase.com)
 |
 *//////////////////////////////////////////////////////////////////////////////

package com.example.gsvaadinokta.service;

import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@VaadinSessionScope
public class AuthService {
    public OidcUser getUser() {
        return (OidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public String getUserName() {
        return getUser().getFullName();
    }

    public String getUserId() {
        return getUser().getSubject();
    }

    public List<String> getUserGroup() {
        return getUser().getClaimAsStringList("groups");
    }


    public String getEmail() {
        return getUser().getEmail();
    }

    public List<String> getPrincipals() {
        return getUser().getClaimAsStringList("principalIds");
    }
}