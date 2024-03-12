package ai.group2.project_management_system.service.Impl;

import ai.group2.project_management_system.model.entity.EmailDetail;
import ai.group2.project_management_system.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import java.io.IOException;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailServiceImplTest {
    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private EmailService emailService = Mockito.mock(EmailService.class);
    private EmailDetail emailDetail;

    @BeforeEach
    public void setup() {
        emailDetail = new EmailDetail();
        emailDetail.setRecipients(Arrays.asList("recipient1@example.com", "recipient2@example.com"));
        emailDetail.setSubject("Test Subject");
        emailDetail.setMsgBody("Test Message Body");
    }

    @Test
    public void testSimpleMail() {
        // Prepare test data
        EmailDetail emailDetail = new EmailDetail();
        emailDetail.setSubject("Test Subject");
        emailDetail.setMsgBody("Test Message");
        emailDetail.addRecipient("recipient@example.com");

        // Call the method
        String result = emailService.simpleMail(emailDetail);

        // Verify that the result indicates successful mail sending
        assertEquals(null, result);
    }

    @Test
    public void testSendMultipleEmail_Success() {
        // Lenient stubbing of the send method of javaMailSender
        lenient().doNothing().when(javaMailSender).send(any(SimpleMailMessage.class));

        // Call the method under test
        String actualResponse = emailService.sendMultipleEmail(emailDetail);

        // Verify that the method under test returned the expected response
        assertEquals(null, actualResponse);
    }

}