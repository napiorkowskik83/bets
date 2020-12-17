package com.crud.bets.services;

import com.crud.bets.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;


    public String buildBetsWonInfoEmail(String message, String username) {

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("username", username);
        context.setVariable("goodbye_message", "Best Regards,");
        context.setVariable("admin_config", adminConfig);
        return templateEngine.process("mail/bets-won-info-mail", context);
    }
}
