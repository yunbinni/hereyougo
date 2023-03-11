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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        Member writer = memberRepository.findById(writerId).get();

        ChatRoom chatRoom = ChatRoom.builder()
                .post(post)
                .member(member)
                .memberNickname(member.getNickname())
                .writerId(writerId)
                .writerNickname(writer.getNickname())
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

    /**
     * 채팅방 조회
     */
    public ChatRoom getChatRoom(Long roomId){
        return chatRoomRepository.findById(roomId).get();
    }

    /**
     * 채팅방 목록 조회
     */
    public Page<ChatRoom> getChatRoomList(Long writerId, Long memberId, Pageable pageable){
        return chatRoomRepository.findByWriterIdOrMember_IdOrderByResentDateDesc(writerId, memberId, pageable);
    }

    public void updateRecentMessage(Long roomId, String message){
        ChatRoom chatRoom = chatRoomRepository.findById(roomId).get();
        chatRoom.updateRecentMessage(message);
    }

}
