package com.example.gsvaadinokta;

import com.example.gsvaadinokta.service.AuthService;
import com.example.gsvaadinokta.ui.applayout.GroupView;
import com.example.gsvaadinokta.ui.applayout.ManageView;
import com.example.gsvaadinokta.ui.applayout.TenancyView;
import com.example.gsvaadinokta.ui.applayout.UserInfoView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Theme(value = Material.class)
@Route
public class MainView extends AppLayout implements BeforeEnterObserver {

  Tabs tabs;
  Map<String, Tab> tabMap = new HashMap<>();

  public MainView(@Autowired AuthService authService) {
      List<String> groups = authService.getUserGroup();
      tabs = new Tabs(
            getTab("User info", UserInfoView.class),
            getTab("Groups", GroupView.class),
            getTab("Tenancy", TenancyView.class));
    if (groups.contains("Managers")) {
      tabs.add(getTab("Managers", ManageView.class));
    }
    tabs.setOrientation(Tabs.Orientation.VERTICAL);

    addToDrawer(tabs);

    Span appName = new Span("Hi, " + authService.getUserName());

    addToNavbar(true, new DrawerToggle(), appName);
  }

  private Tab getTab(String text, Class<?> clazz) {
    final String route = clazz.getAnnotation(Route.class).value();
    Tab tab = new Tab();
    tab.add(new Anchor(route, text));
    tabMap.put(route, tab);
    return tab;
  }

  @Override
  public void beforeEnter(BeforeEnterEvent event) {
    tabs.setSelectedTab(tabMap.get(event.getLocation().getPath()));
  }
}
