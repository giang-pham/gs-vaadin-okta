package com.example.gsvaadinokta.ui.applayout;

import com.example.gsvaadinokta.MainView;
import com.example.gsvaadinokta.service.AuthService;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "userinfo", layout = MainView.class)
public class UserInfoView extends VerticalLayout {

    public UserInfoView(@Autowired AuthService authService) {
        Div div = new Div();
        div.add(new Paragraph(String.format("Id: %s", authService.getUserId())));
        div.add(new Paragraph(String.format("Login: %s", authService.getEmail())));
        add(div);
    }
}
