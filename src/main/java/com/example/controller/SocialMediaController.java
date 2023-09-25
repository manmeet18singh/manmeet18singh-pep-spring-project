package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
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
        return messageService.postNewMessage(newMessage, accountExists);
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
    private ResponseEntity<Message> getMessageById(@PathVariable Integer message_id) {
        return messageService.getMessageById(message_id);
    }

    /*
     * User Story 6: Our API should be able to delete a message identified by a
     * message ID.
     */
    @DeleteMapping("/messages/{message_id}")
    private ResponseEntity<Integer> deleteMessageById(@PathVariable Integer message_id) {
        return messageService.deleteMessageById(message_id);
    }

    /*
     * User Story 7: Our API should be able to update a message text identified by a
     * message ID.
     */
    @PatchMapping("/messages/{message_id}")
    private ResponseEntity<Message> updateMessageById(@PathVariable Integer message_id,
            @RequestBody Message updatedMessage) {
        return messageService.updateMessageById(message_id, updatedMessage);
    }

    /*
     * User Story 8: Our API should be able to retrieve all messages written by a
     * particular user.
     */
    @GetMapping("/accounts/{account_id}/messages")
    private ResponseEntity<List<Message>> getMessageByAccountId(@PathVariable Integer account_id) {
        return messageService.getMessageByAccountId(account_id);
    }
}
