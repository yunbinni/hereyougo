package io.k2c1.hereyougo.service;

import io.k2c1.hereyougo.domain.ChatMessage;
import io.k2c1.hereyougo.domain.ChatRoom;
import io.k2c1.hereyougo.dto.ChatForm;
import io.k2c1.hereyougo.dto.ChatMessageResponseDTO;
import io.k2c1.hereyougo.repository.ChatMessageRepository;
import io.k2c1.hereyougo.repository.ChatRoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional
@Service
public class ChatMessageService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public ChatMessage createChatMessage(ChatForm chatForm){
        
        log.info("채팅 메시지 저장하는 곳 , 들어오는지");
        Long roomId = chatForm.getChatRoomId();

        ChatRoom chatRoom = chatRoomRepository.findById(roomId).orElseThrow();

        ChatMessage message = ChatMessage.builder()
                .message(chatForm.getMessage())
                .sender(chatForm.getSender())
                .chatRoom(chatRoom)
                .build();

        ChatMessage result = chatMessageRepository.save(message);

        if(result != null){
            chatRoom.updateRecentDate(LocalDateTime.now());
        }

        return result;
    }

    /**
     * 채팅방 내 채팅내역 조회
     */
    public List<ChatMessageResponseDTO> getChatMessageByRoomId(Long roomId){
        List<ChatMessage> result = chatMessageRepository.findByChatRoom_id(roomId);
        List<ChatMessageResponseDTO> chatMessages = new ArrayList<>();

        for(ChatMessage chatMessage : result){
            ChatMessageResponseDTO dto = ChatMessageResponseDTO.builder()
                    .message(chatMessage.getMessage())
                    .sender(chatMessage.getSender())
                    .sendDate(chatMessage.getSendDate())
                    .build();

            chatMessages.add(dto);
        }

        return chatMessages;
    }

}
