package com.example.controller;

import java.util.List;

import javax.persistence.PostUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    /*
     * User Story 2: Our API should be able to process User logins.
     */

    @PostMapping(value = "/login")
    private ResponseEntity<Account> postLoginAccount(@RequestBody Account accountToLogin) {
        return accountService.loginAccount(accountToLogin);
    }

    /*
     * User Story 3: Our API should be able to process the creation of new messages.
     */
    @PostMapping(value = "/messages")
    private ResponseEntity<Message> postNewMessage(@RequestBody Message newMessage) {
        boolean accountExists = accountService.getAccountById(newMessage.getPosted_by());

        return messageService.newMessage(newMessage, accountExists);
    }

    /*
     * User Story 4: Our API should be able to retrieve all messages.
     */
    @GetMapping("/messages")
    private ResponseEntity<List<Message>> getAllMessages() {
        return messageService.getAllMessages();
    }

    /*
     * User Story 5: Our API should be able to retrieve a message by its ID.
     */
    @GetMapping("/messages/{message_id}")
    private ResponseEntity<Message> getMessageById(@PathVariable Integer messageId) {
        return messageService.getMessageById(messageId);
    }

    /*
     * User Story 6: Our API should be able to delete a message identified by a
     * message ID.
     */
    @DeleteMapping("/messages/{message_id}")
    private ResponseEntity<Message> deleteMessageById(@PathVariable Integer messageId) {
        return messageService.deleteMessageById(messageId);
    }

    /*
     * User Story 7: Our API should be able to update a message text identified by a
     * message ID.
     */
    @PatchMapping("/messages")
    private ResponseEntity<Message> updateMessageById(@RequestBody Message updatedMessage) {
        return messageService.updateMessageById(updatedMessage);
    }

    /*
     * User Story 8: Our API should be able to retrieve all messages written by a
     * particular user.
     */
    @GetMapping("/accounts/{account_id}/messages")
    private ResponseEntity<List<Message>> getMessageByAccountId(@PathVariable Integer accountId) {
        return messageService.getMessageByAccountId(accountId);
    }
}
