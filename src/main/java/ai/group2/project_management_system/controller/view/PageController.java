package ai.group2.project_management_system.controller.view;


import ai.group2.project_management_system.model.entity.EmailDetail;
import ai.group2.project_management_system.model.entity.Message;
import ai.group2.project_management_system.service.EmailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;

@Controller
public class PageController {
    @Autowired
    private EmailService service;

    // Sending a simple Email
    @PostMapping("/forgot-password")
    public String sendMail(@RequestParam String recipient,
                           @RequestParam String subject, HttpSession session) {

        // Create an EmailDetail object and set the values
        EmailDetail details = new EmailDetail();
        details.setRecipients(Collections.singletonList(recipient));
        details.setSubject(subject);
        String OTP = service.generateOTP();
        details.setMsgBody(OTP);
        session.setAttribute("otp", OTP);

        {


            // Call the service method to send the email
            String status = service.simpleMail(details);

            return "otp-form";
        }

    }


    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/forgot-password")
    public String forgotPasswordGet() {
        return "forgot-password";
    }

    @PostMapping("/otp-form")
    public String otpFormSubmit(@RequestParam String OTPInput, HttpSession session, Model model) {

        String OTP = (String) session.getAttribute("otp");

        // Validate OTPs using equals() for content comparison
        if (OTPInput.equals(OTP)) {
            // OTPs match, redirect to create-new-password
            return "create-new-password";
        } else {

            // OTPs don't match, redirect back to otp-form
            model.addAttribute("otpError", "Your OTP is shit, please type a correct one. Thanks");
            return "otp-form";
        }

    }

    @GetMapping("/email-attachment-testing")
    public String emailAttachment() {

        return "email-attachment-testing";
    }

    @PostMapping("/sendEmailWithAttachment")
    public String sendEmailWithAttachment(@RequestParam String recipient,
                                          @RequestParam String subject,
                                          @RequestParam String message,
                                          @RequestParam(required = false) MultipartFile attachment) {


         service.sendEmailWithAttachment(recipient, subject, message, attachment);

         return "login";

    }

    @GetMapping("/calendar")
    public String getCalendar(){

        return "calendar";
    }

    @GetMapping("/sendMultipleEmail")
    public String getEmailForm(){

        return "multiple-email-selection";
    }

    @PostMapping("/sendMultipleEmail")
    public String sendMultipleEmail(@RequestBody EmailDetail email){

        System.out.println("Emails :  "+ email.getRecipients());
        System.out.println("Subject :  "+ email.getSubject());
        System.out.println("message :  "+ email.getMsgBody());



        service.sendMultipleEmail(email);

        return "login";
    }
    @GetMapping("/aaa/testChat")
    public String index() {
        return "chat-box";
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message sendMessage(Message message) {


        String sender = message.getSender();
        String content = message.getContent();


        System.out.println("sender   : "+ sender);
        System.out.println("message   : "+ content);

        // Process the incoming message (e.g., store in a database) and return it
        return message;
    }



}
