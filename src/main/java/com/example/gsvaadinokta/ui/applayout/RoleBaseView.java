package com.example.gsvaadinokta.ui.applayout;

import com.example.gsvaadinokta.auth.HasValidRole;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;

public interface RoleBaseView extends BeforeEnterObserver {


    @Override
    default void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (!validAuth())
            beforeEnterEvent.rerouteTo(Auth403View.class);
    }

    default boolean validAuth() {
        return HasValidRole.valid(this.getClass());
    }


}
