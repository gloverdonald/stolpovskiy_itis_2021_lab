package ru.itis.finalproject.util;

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

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class EmailUtil {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    private final Configuration freemarkerConfiguration;

    public void sendMail(String to, String subject, String templateName, Map<String, Object> data) {
        try {
            String str = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfiguration.getTemplate(templateName, "UTF-8"), data);
            MimeMessagePreparator preparator = mimeMessage -> {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
                messageHelper.setSubject(subject);
                messageHelper.setText(str, true);
                messageHelper.setTo(to);
                messageHelper.setFrom(from);
            };
            mailSender.send(preparator);
        } catch (IOException | TemplateException e) {
            System.out.println("ftl not found");
        }
    }
}
