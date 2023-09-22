package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    // Repos are where we do our queries... simillar to the DAO files in the
    // previous project

    List<Message> findMessageByPostedBy(Integer posted_by);
}
