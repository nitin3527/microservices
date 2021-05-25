package com.ttn.project.customermicroservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
public class EmailSender {
    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public void sendMail(SimpleMailMessage simpleMailMessage)
    {
        javaMailSender.send(simpleMailMessage);
    }

    @Async
    public void sendEmail(String email,String subject,String text)
    {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);
    }
}
