package com.project2.ayurcare.ayurcare_backend.jpaResource;

import com.project2.ayurcare.ayurcare_backend.Service.EmailSenderService;
import com.project2.ayurcare.ayurcare_backend.entity.listener.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailJpaResources {

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/email/booking/confirmation")
    private Boolean confirmEmail(@RequestBody Email email) {
        return emailSenderService.sendEmail(email);
    }
}
