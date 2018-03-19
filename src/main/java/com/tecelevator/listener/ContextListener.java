package com.tecelevator.listener;


import org.jinstagram.auth.InstagramAuthService;
import org.jinstagram.auth.oauth.InstagramService;

import com.tecelevator.instagram.Constants;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {

        String clientId = "27ce4c113f9b4d0296f1721999370707";
        String clientSecret = "fe3bd9c0510b4d4f8fb71f5467bd8b9a";
        String callbackUrl = "https://safe-ridge-31475.herokuapp.com/handleInstagramToken";



        InstagramService service = new InstagramAuthService()
                .apiKey(clientId)
                .apiSecret(clientSecret)
                .callback(callbackUrl)
                .build();

        sce.getServletContext().setAttribute(Constants.INSTAGRAM_SERVICE, service);

    }

    public void contextDestroyed(ServletContextEvent sce) {

        sce.getServletContext().removeAttribute(Constants.INSTAGRAM_SERVICE);

    }


}