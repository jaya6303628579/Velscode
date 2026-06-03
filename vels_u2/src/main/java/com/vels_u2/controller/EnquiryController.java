package com.vels_u2.controller;

import com.vels_u2.dto.EnquiryRequest;
import com.vels_u2.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enquiry")
@CrossOrigin(origins = "*")
public class EnquiryController {

    private final EmailService emailService;

    public EnquiryController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public ResponseEntity<String> submitEnquiry(
            @RequestBody EnquiryRequest request) {

        emailService.sendEnquiry(request);

        return ResponseEntity.ok(
                "Enquiry submitted successfully");
    }
}