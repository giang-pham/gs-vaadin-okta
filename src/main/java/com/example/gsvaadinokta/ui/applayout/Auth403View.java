package com.example.gsvaadinokta.ui.applayout;

import com.example.gsvaadinokta.MainView;
import com.example.gsvaadinokta.service.AuthService;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "403", layout = MainView.class)
public class Auth403View extends VerticalLayout {

    public Auth403View(@Autowired AuthService authService) {
        Div div = new Div();
        div.add(new Paragraph(String.format("[403] Forbidden for : %s", authService.getUserId())));

        add(div);
    }
}
