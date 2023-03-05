package io.k2c1.hereyougo.repository;

import io.k2c1.hereyougo.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    /**
     * 이미 존재하는 채팅방인지 확인
     */
    public Optional<ChatRoom> findByPost_IdAndMember_Id(Long postId, Long memberId);

    /**
     * 채팅 목록 조회
     */
    public List<ChatRoom> findByWriterIdOrMember_IdOrderByResentDateDesc(Long writerId, Long memberId);

}
