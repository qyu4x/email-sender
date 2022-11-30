package coffekyun.MailSender.service;

import coffekyun.MailSender.entity.EmailDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.activation.DataSource;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public String sendSimpleEmail() {
        try {
            log.info("sent email is processing...");
            MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);


            //SimpleMailMessage mailMessage = new SimpleMailMessage();
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo("qq.khoiri@gmail.com");
            mimeMessageHelper.setText("<h1>Hey! \n\n I love you so much \n\n Aishiteru ~ From Tsukasa Tsukoyomi</h1> <hr><img src='cid:logoImage'/>", true);
            mimeMessageHelper.setSubject("Aishiteru ");
            mimeMessageHelper.setSentDate(new Date());

            ClassPathResource classPathResource = new ClassPathResource("/images/anime1.png");
            mimeMessageHelper.addInline("logoImage", classPathResource);

            ClassPathResource classPathResourcePdf = new ClassPathResource("/pdf/carrear.pdf");

            mimeMessageHelper.addAttachment("carrear.pdf", classPathResourcePdf);
            javaMailSender.send(mimeMailMessage);
            log.info("Mail sent successfully...");
            return "Mail sent successfully";
        }catch (Exception exception) {
            log.info("Error is accours {}", exception.getMessage());
        }

        return null;
    }

//    @Scheduled(cron = "0 0/1 * * * ?")
//    public void sendEmailWithSchedule() {
//        doSendEmail();
//    }
//
//    void doSendEmail(){
//        System.out.println(" hello " + new Date());
//        EmailDetails emailDetails = new EmailDetails();
//        emailDetails.setRecipient("qq.khoiri@gmail.com");
//        emailDetails.setSubject("Test");
//        log.info("send email {} ", new Date());
//        emailDetails.setMessageBody("Hey! \n\n I love you ~ From Tsukasa Tsukoyomi");
//
//        log.info("send email is processing...");
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setFrom(sender);
//        mailMessage.setTo(emailDetails.getRecipient());
//        mailMessage.setText(emailDetails.getMessageBody());
//        mailMessage.setSubject(emailDetails.getSubject());
//        mailMessage.setSentDate(new Date());
//        log.info("Mail send successfully...");
//    }
}
