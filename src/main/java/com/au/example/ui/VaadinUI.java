package com.au.example.ui;

import javax.servlet.annotation.WebServlet;

import com.au.example.service.UserService;
import com.au.example.ui.view.LoginPage;
import com.au.example.ui.view.OtherSecurePage;
import com.au.example.ui.view.SecurePage;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.Page.UriFragmentChangedEvent;
import com.vaadin.server.Page.UriFragmentChangedListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@Theme("valo")
public class VaadinUI extends UI {


    @Autowired
    UserService userService;


    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = VaadinUI.class)
    public static class Servlet extends VaadinServlet {
    }
    @Override
    protected void init(VaadinRequest request) {
        new Navigator(this, this);

        getNavigator().addView(LoginPage.NAME,new LoginPage(userService));
        getNavigator().setErrorView(LoginPage.class);

        Page.getCurrent().addUriFragmentChangedListener(new UriFragmentChangedListener() {

            @Override
            public void uriFragmentChanged(UriFragmentChangedEvent event) {
                router(event.getUriFragment());
            }
        });


        router("");
    }

    private void router(String route){
        Notification.show(route);
        if(getSession().getAttribute("user") != null){
            getNavigator().addView(SecurePage.NAME, SecurePage.class);
            getNavigator().addView(OtherSecurePage.NAME, OtherSecurePage.class);
            if(route.equals("!OtherSecure")){
                getNavigator().navigateTo(OtherSecurePage.NAME);
            }else{
                getNavigator().navigateTo(SecurePage.NAME);
            }
        }else{
            getNavigator().navigateTo(LoginPage.NAME);
        }
    }
}