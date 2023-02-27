package io.k2c1.hereyougo.repository;

import io.k2c1.hereyougo.domain.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByChatRoom_id(Long chatRoomId);
}
