package coffekyun.MailSender.service;

import coffekyun.MailSender.entity.EmailDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public String sendSimpleEmail(EmailDetails emailDetails) {
        try {
            log.info("sent email is processing...");
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(emailDetails.getRecipient());
            mailMessage.setText(emailDetails.getMessageBody());
            mailMessage.setSubject(emailDetails.getSubject());
            mailMessage.setSentDate(new Date());

            javaMailSender.send(mailMessage);
            log.info("Mail sent successfully...");
            return "Mail sent successfully";
        }catch (Exception exception) {
            log.info("Error is accours {}", exception.getMessage());
        }

        return null;
    }
}
