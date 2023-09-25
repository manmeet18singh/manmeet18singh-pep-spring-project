package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    // Repos are where we do our queries... simillar to the DAO files in the
    // previous project
    @Query("FROM message WHERE posted_by = :postId")
    List<Message> findByPostedBy(@Param("postId") Integer posted_by);

    @Modifying
    @Query("DELETE FROM message WHERE message_id = :id")
    int deleteByMessageId(@Param("id") Integer message_id);
}