package coffekyun.MailSender.service;

import coffekyun.MailSender.entity.EmailDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmailServiceImplTest {

    @Autowired
    private EmailService emailService;

    @Test
    void testSendEmail() {

        emailService.sendSimpleEmail();

    }
}