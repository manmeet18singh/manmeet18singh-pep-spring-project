package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepo;

    public ResponseEntity<Message> postNewMessage(Message newMessage, boolean accountExists) {

        if (newMessage.getMessage_text() != "" && newMessage.getMessage_text().length() < 255 && accountExists) {

            return new ResponseEntity<Message>(messageRepo.save(newMessage), HttpStatus.OK);

        }
        return new ResponseEntity<Message>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Message>> getAllMessages() {

        return new ResponseEntity<List<Message>>(messageRepo.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Message> getMessageById(Integer messageId) {
        if (!messageRepo.findById(messageId).isEmpty()) {
            return new ResponseEntity<Message>(messageRepo.findById(messageId).get(), HttpStatus.OK);
        }
        return new ResponseEntity<Message>(HttpStatus.OK);
    }

    public ResponseEntity<Integer> deleteMessageById(Integer messageId) {
        if (!messageRepo.findById(messageId).isEmpty()) {
            new ResponseEntity<Integer>(messageRepo.deleteByMessageId(messageId), HttpStatus.OK);
        }
        return new ResponseEntity<Integer>(HttpStatus.OK);
    }

    public ResponseEntity<Message> updateMessageById(Integer messageId, Message updatedMessage) {
        if (!messageRepo.findById(messageId).isEmpty() && updatedMessage.getMessage_text() != ""
                && updatedMessage.getMessage_text().length() < 255) {

            Message message = messageRepo.findById(messageId).get();
            message.setMessage_text(updatedMessage.getMessage_text());

            return new ResponseEntity<Message>(messageRepo.save(message), HttpStatus.OK);
        }

        return new ResponseEntity<Message>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Message>> getMessageByAccountId(Integer accountId) {
        return new ResponseEntity<List<Message>>(messageRepo.findByPostedBy(accountId), HttpStatus.OK);
    }
}
