package io.k2c1.hereyougo.service;

import io.k2c1.hereyougo.domain.ChatRoom;
import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.domain.Post;
import io.k2c1.hereyougo.dto.RoomForm;
import io.k2c1.hereyougo.repository.ChatRoomRepository;
import io.k2c1.hereyougo.repository.MemberRepository;
import io.k2c1.hereyougo.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional
@Service
public class ChatRoomService {
    @Autowired
    private ChatRoomRepository chatRoomRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private MemberRepository memberRepository;

    public ChatRoom createChatRoom(RoomForm roomForm){
        Post post = postRepository.findById(roomForm.getPostId()).get();
        Long writerId = post.getWriter().getId();
        Member member = memberRepository.findById(roomForm.getMemberId()).get();

        ChatRoom chatRoom = ChatRoom.builder()
                .post(post)
                .member(member)
                .writerId(writerId)
                .build();

        chatRoomRepository.save(chatRoom);

        log.info("채팅방 잘 만들어졌는지" +  chatRoom.toString());

        return chatRoom;
    }

    /**
     * 이미 존재하는 채팅방인지 체크
     */
    public Optional<ChatRoom> isExistChatRoom(Long postId, Long memberId){
        return chatRoomRepository.findByPost_IdAndMember_Id(postId, memberId);
    }
}
