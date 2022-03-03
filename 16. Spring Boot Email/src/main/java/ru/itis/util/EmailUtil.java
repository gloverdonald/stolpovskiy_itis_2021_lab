package ru.itis.util;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import ru.itis.models.Account;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class EmailUtil {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private Configuration freemarkerConfiguration;

    public void sendMail(Account account) {
        try {
            Map<String, Object> model = new HashMap<>();
            model.put("account", account);
            String str = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfiguration.getTemplate("/emails/confirm_mail.ftlh", "UTF-8"), model);
            MimeMessagePreparator preparator = mimeMessage -> {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
                messageHelper.setSubject("subject");
                messageHelper.setText(str, true);
                messageHelper.setTo(account.getEmail());
                messageHelper.setFrom(from);
            };
            mailSender.send(preparator);
        } catch (IOException | TemplateException e) {
            System.out.println("ftl not found");
        }
    }
}
