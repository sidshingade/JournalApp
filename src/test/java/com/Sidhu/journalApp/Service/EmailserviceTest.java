package com.Sidhu.journalApp.Service;

import com.Sidhu.journalApp.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailserviceTest {

    @Autowired
    private EmailService emailService;

    @Test
    void sendMail(){
        emailService.sendMail("sidhushingade488@gmail.com","Testing Java Mail Sender","App Kaise hai");
    }


}
