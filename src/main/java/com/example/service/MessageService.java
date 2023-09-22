package com.example.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.azul.crs.client.Response;
import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepo;

    public ResponseEntity<Message> newMessage(Message newMessage, boolean accountExists) {

        if (newMessage.getMessage_text() != "" && newMessage.getMessage_text().length() < 255 && accountExists) {

            return new ResponseEntity<Message>(messageRepo.save(newMessage), HttpStatus.OK);

        }
        return new ResponseEntity<Message>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Message>> getAllMessages() {

        return new ResponseEntity<List<Message>>(messageRepo.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Message> getMessageById(Integer messageId) {
        return new ResponseEntity<Message>(messageRepo.getById(messageId), HttpStatus.OK);
    }

    public ResponseEntity<Message> deleteMessageById(Integer messageId) {
        return new ResponseEntity<Message>(HttpStatus.OK);
    }

    public ResponseEntity<Message> updateMessageById(Message updatedMessage) {
        if (!messageRepo.findById(updatedMessage.getMessage_id()).isEmpty() && updatedMessage.getMessage_text() != ""
                && updatedMessage.getMessage_text().length() < 255) {
            Message message = messageRepo.findById(updatedMessage.getMessage_id()).get();
            return new ResponseEntity<Message>(messageRepo.save(message), HttpStatus.OK);
        }

        return new ResponseEntity<Message>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Message>> getMessageByAccountId(Integer accountId) {
        return new ResponseEntity<List<Message>>(messageRepo.findMessageByPostedBy(accountId), HttpStatus.OK);
    }
}
