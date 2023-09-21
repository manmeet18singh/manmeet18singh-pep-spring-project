package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepo;

    public List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<>();
        messageRepo.findAll().forEach(message -> messages.add(message));

        return messages;
    }
}
