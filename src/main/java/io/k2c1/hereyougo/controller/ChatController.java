package io.k2c1.hereyougo.controller;

import io.k2c1.hereyougo.constant.SessionConst;
import io.k2c1.hereyougo.domain.ChatMessage;
import io.k2c1.hereyougo.domain.ChatRoom;
import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.domain.Post;
import io.k2c1.hereyougo.dto.ChatForm;
import io.k2c1.hereyougo.dto.ChatMessageResponseDTO;
import io.k2c1.hereyougo.repository.MemberRepository;
import io.k2c1.hereyougo.repository.PostRepository;
import io.k2c1.hereyougo.service.ChatMessageService;
import io.k2c1.hereyougo.service.ChatRoomService;
import io.k2c1.hereyougo.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Slf4j
@Controller
public class ChatController {

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private PostService postService;

    @Autowired
    private MemberRepository memberRepository;

    @MessageMapping("/{roomId}")
    @SendTo("/room/{roomId}")
    public ChatMessage chat(@DestinationVariable String roomId, ChatForm chatForm){
        ChatMessage chatMessage = chatMessageService.createChatMessage(chatForm);

        Long reqRoomId = Long.parseLong(roomId);
        ChatRoom chatRoom = chatRoomService.getChatRoom(reqRoomId);

        chatRoomService.updateRecentMessage(reqRoomId, chatForm.getMessage());

        ChatMessage resChatMessage = ChatMessage.builder()
                .chatRoom(chatRoom)
                .sender(chatMessage.getSender())
                .message(chatMessage.getMessage())
                .build();

        return resChatMessage;
    }

    @GetMapping("/chat/{roomId}")
    public String joinChatRoom(@PathVariable(required = false) Long roomId, @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model){
        List<ChatMessageResponseDTO> chatList = chatMessageService.getChatMessageByRoomId(roomId);
        ChatRoom chatRoom = chatRoomService.getChatRoom(roomId);

        Long postId = chatRoom.getPost().getId();

        Post post = postService.getPost(postId).get();

        model.addAttribute("roomId", roomId);
        model.addAttribute("writerId", chatRoom.getWriterId());
        model.addAttribute("chatRoom", chatRoom);
        model.addAttribute("loginMember", loginMember);
        model.addAttribute("post", post);
        model.addAttribute("chatList", chatList);
        model.addAttribute("member", loginMember);
        return "chat/chatting";
    }
}
