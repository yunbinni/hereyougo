package io.k2c1.hereyougo.service;

import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.repository.MemberRepository;
import io.k2c1.hereyougo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class PostService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PostRepository postRepository;

    public void deleteByWriter(Long memberId){
        Member member = memberRepository.findById(memberId).get();
        postRepository.deleteByWriter(member);
    }
}
