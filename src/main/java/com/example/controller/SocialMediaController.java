package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your
 * controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use
 * the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations.
 * You should
 * refer to prior mini-project labs and lecture materials for guidance on how a
 * controller may be built.
 */
@RestController
public class SocialMediaController {

    @Autowired
    private AccountService accountService;
    private MessageService messageService;

    /*
     * User Story 1: Our API should be able to process new User registrations.
     */

    @PostMapping(value = "/register")
    private ResponseEntity<Account> postRegisterAccount(@RequestBody Account newAccount) {

        return accountService.registerAccount(newAccount);
    }

    /**
     * User Story 4: Our API should be able to retrieve all messages.
     * 
     */
    // @GetMapping("/messages")
    // private List<Message> getAllMessages() {
    // return messageService.getAllMessages();
    // }

}
