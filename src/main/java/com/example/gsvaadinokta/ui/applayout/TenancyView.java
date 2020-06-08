package com.example.gsvaadinokta.ui.applayout;

import com.example.gsvaadinokta.MainView;
import com.example.gsvaadinokta.service.AuthService;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "tenancy", layout = MainView.class)
public class TenancyView extends VerticalLayout {

    public TenancyView(@Autowired AuthService authService) {
        final List<String> principalIds = authService.getPrincipals();
        Accordion accordion = new Accordion();
        accordion.setWidthFull();
        if (principalIds == null || principalIds.isEmpty()) {
            accordion.add("None", new Label("tenancy not setup on user profile"));
        } else

            for (String principalId : principalIds) {
                accordion.add(principalId, new Paragraph("TODO: tenancy view:" + principalId));
            }
        add(accordion);
    }
}
