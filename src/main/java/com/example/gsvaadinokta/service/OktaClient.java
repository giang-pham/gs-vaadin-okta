package com.example.gsvaadinokta.service;

import com.okta.sdk.authc.credentials.TokenClientCredentials;
import com.okta.sdk.client.Client;
import com.okta.sdk.client.Clients;
import com.okta.sdk.resource.group.Group;
import com.okta.sdk.resource.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class OktaClient {

  public static final String ALL_TENANT = "ALL";
  public static final String PRINCIPAL_IDS = "principalIds";
  private final Client client;

    public OktaClient(@Value( "${okta.token}" ) String apiToken,
                      @Value( "${okta.org}" ) String org) {
        ClassLoader originalClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
            client = Clients.builder()
                    .setClientCredentials(new TokenClientCredentials(apiToken))
                    .setOrgUrl(org)
                    .build();
        } finally {
            Thread.currentThread().setContextClassLoader(originalClassLoader);
        }
    }

    public User getUser(String uid) {
        return client.getUser(uid);
    }

    public List<String> getTenants(String uid) {
      final List<String> principalIds = Optional.ofNullable(getUser(uid).getProfile().getStringList(PRINCIPAL_IDS)).orElse(Collections.emptyList());

      if (principalIds.contains(ALL_TENANT)) {
        return getAllUser().stream().flatMap(u -> getPrincipalIdsFromUser(u).stream())
            .filter(i->!i.equalsIgnoreCase(ALL_TENANT)).collect(Collectors.toList());
      }
      return principalIds;
    }

    public List<User> getUserByTenant(String principalId) {
        return getAllUser().stream().filter(userBelongToTenant(principalId)).collect(Collectors.toList());
    }

    public List<Group> getGroups(String uid) {
        return client.getUser(uid).listGroups().stream().collect(Collectors.toList());
    }

    public List<User> getAllUser() {
      return client.listUsers().stream().collect(Collectors.toList());
    }

    private Predicate<User> userBelongToTenant(String principalId) {
      return u -> Optional.ofNullable(getPrincipalIdsFromUser(u))
          .map(l -> l.contains(principalId) || l.contains(ALL_TENANT))
          .orElse(false);
    }

    private List<String> getPrincipalIdsFromUser(User user) {
      return Optional.ofNullable(user.getProfile().getStringList(PRINCIPAL_IDS)).orElse(Collections.emptyList());
    }

}
