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

@Route(value = "groups", layout = MainView.class)
public class GroupView extends VerticalLayout {

  public GroupView(@Autowired AuthService authService) {
      List<String> groups = authService.getUserGroup();
      Accordion accordion = new Accordion();
      accordion.setWidthFull();
      if (groups == null || groups.isEmpty()) {
          accordion.add("NONE",new Label("group not setup on user profile"));
      } else {
        for (String group : groups) {
            accordion.add(group, new Paragraph("TODO: Group view:" + group));
        }
      }
      add(accordion);
  }
}
