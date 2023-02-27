package io.k2c1.hereyougo.controller;

import io.k2c1.hereyougo.constant.SessionConst;
import io.k2c1.hereyougo.domain.ChatRoom;
import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.domain.Post;
import io.k2c1.hereyougo.dto.ChatMessageResponseDTO;
import io.k2c1.hereyougo.dto.RoomForm;
import io.k2c1.hereyougo.service.ChatMessageService;
import io.k2c1.hereyougo.service.ChatRoomService;
import io.k2c1.hereyougo.service.MemberService;
import io.k2c1.hereyougo.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequestMapping("/chatroom")
@Controller
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private PostService postService;

    @Autowired
    private MemberService memberService;

    /**
     * 채팅방 생성
     */
    @PostMapping("/new")
    public String createRoom(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                                   String postId, Model model){
        log.info("포스트 아이디" + postId);
        log.info("회원 아이디" + loginMember.getId());
        ChatRoom chatRoom;
        List<ChatMessageResponseDTO> chatList;

        Long resPostId = Long.parseLong(postId);
        Long memberId = loginMember.getId();

        RoomForm roomForm = new RoomForm();
        roomForm.setMemberId(memberId);
        roomForm.setPostId(resPostId);

//      채팅방이 있는지 체크하는 변수
        Optional<ChatRoom> isChatRoom = chatRoomService.isExistChatRoom(resPostId, memberId);

//      채팅방이 없는 경우, 채팅방 생성
        if(isChatRoom.isEmpty()){
            chatRoom = chatRoomService.createChatRoom(roomForm);
            chatList = null;
        }else{
            chatRoom = isChatRoom.get();
            chatList = chatMessageService.getChatMessageByRoomId(chatRoom.getId());
        }

        String roomId = Long.toString(chatRoom.getId());

        Map<String, String > result = new HashMap<>();
        result.put("roomId", roomId);

        Long convertPostId = Long.parseLong(postId);

        Post post = postService.getPost(convertPostId).get();

        log.info("채팅방 아이디 잘 넘겨질까?" + chatRoom.getId());
        model.addAttribute("roomId", chatRoom.getId());
        model.addAttribute("loggedMemberId", memberId);
        model.addAttribute("chatRoom", chatRoom);
        model.addAttribute("post", post);
        model.addAttribute("chatList", chatList);
        if (loginMember != null) model.addAttribute("member", loginMember);


//        return "redirect:/chatroom/enter?roomId="+ roomId;
//        return roomId;
        return "chat/chatting";
    }

    /**
     * 채팅방 목록
     */
    @GetMapping("/roomList")
    public String roomList(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model){
        Long writerId = loginMember.getId();
        Long memberId = loginMember.getId();
        List<ChatRoom> chatRoomList = chatRoomService.getChatRoomList(writerId, memberId);

//        log.info("로그인 멤버" + loginMember);
        model.addAttribute("loginMember", loginMember);
        model.addAttribute("member", loginMember);
        model.addAttribute("chatRoomList", chatRoomList);
        return "chat/chatRoomList";
    }

    /**
     * 채팅방 문의하기
     */
    @GetMapping("/enter")
    public String moveChatRoom(@RequestParam("roomId") String roomId, @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model){

        log.info("/enter로 들어오는지");

        model.addAttribute("roomId", Long.parseLong(roomId));

        log.info("채팅방 아이디" +  roomId);

        return "chat/chatting";
    }


}
