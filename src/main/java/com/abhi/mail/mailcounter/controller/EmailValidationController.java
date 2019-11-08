package com.abhi.mail.mailcounter.controller;

import com.abhi.mail.mailcounter.model.EmailWrapper;
import com.abhi.mail.mailcounter.validator.ValidatorUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailValidationController {
    private static final Logger logger = LoggerFactory.getLogger(EmailValidationController.class);

    @RequestMapping(method = RequestMethod.POST, value="/emails")
    public ResponseEntity<Integer> countValidEmailId(@RequestBody EmailWrapper emailWrapper){
        return new ResponseEntity<Integer>(ValidatorUtility
                .validEmailAddressCount(emailWrapper.getEmails()),HttpStatus.OK);
    }
}
