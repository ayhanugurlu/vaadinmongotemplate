package com.au.example.ui.view;


import com.au.example.exception.InvalidUserNameOrPassword;
import com.au.example.rest.model.response.LoginResp;
import com.au.example.service.UserService;
import com.au.example.service.model.LoginDto;
import com.au.example.ui.VaadinUI;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;


public class LoginPage extends VerticalLayout implements View {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "Login";

    private UserService userService;


    public LoginPage(UserService userService){
        this.userService  = userService;
        Panel panel = new Panel("Login");
        panel.setSizeUndefined();
        addComponent(panel);


        FormLayout content = new FormLayout();
        TextField username = new TextField("Username");
        content.addComponent(username);
        PasswordField password = new PasswordField("Password");
        content.addComponent(password);

        Button send = new Button("Enter");
        send.addClickListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                LoginDto loginDto = new LoginDto();
                loginDto.setUsername(username.getValue());
                loginDto.setPassword(password.getValue());
                try {
                    LoginResp loginResp = LoginPage.this.userService.login(loginDto);

                    VaadinSession.getCurrent().setAttribute("user", loginResp.getToken());
                    getUI().getNavigator().addView(SecurePage.NAME, SecurePage.class);
                    getUI().getNavigator().addView(OtherSecurePage.NAME, OtherSecurePage.class);
                    Page.getCurrent().setUriFragment("!"+SecurePage.NAME);
                }catch (InvalidUserNameOrPassword e){
                    Notification.show("Invalid credentials", Notification.Type.ERROR_MESSAGE);
                }


            }

        });
        content.addComponent(send);
        content.setSizeUndefined();
        content.setMargin(true);
        panel.setContent(content);
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

    }

    @Override
    public void enter(ViewChangeEvent event) {

    }

}