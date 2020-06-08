package com.example.gsvaadinokta.ui.applayout;

import com.example.gsvaadinokta.MainView;
import com.example.gsvaadinokta.auth.ViewRole;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "manage", layout = MainView.class)
@ViewRole("Managers")
public class ManageView extends VerticalLayout implements RoleBaseView {

    public ManageView() {
        Div div = new Div();
        div.add(new Paragraph("TODO: Manage View"));
        add(div);
    }

}
