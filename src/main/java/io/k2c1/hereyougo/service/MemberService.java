package io.k2c1.hereyougo.service;

import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.dto.MemberUpdateForm;
import io.k2c1.hereyougo.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /***
     * 회원가입
     */
    public Long join(Member member){
        isDuplicateMember(member); // 회원 이메일 중복 검사
        memberRepository.save(member);
        return member.getId();
    }

    /***
     * 회원정보 수정
     */
    public void updateMemberInfo(MemberUpdateForm updateForm){
        Member result = memberRepository.findById(updateForm.getId()).get();

        result.updateMemberInfo(
                updateForm.getPassword(),
                updateForm.getNickname(),
                updateForm.getBusinessType()
        );
    }

    /***
     * 회원정보 상세 조회
     */
    public Member findMember(Long memberId){
        return memberRepository.findById(memberId).get();
    }


    /***
     * 회원 탈퇴
     */
    public void deleteMember(Member member){
        memberRepository.delete(member);
    }


    /***
     * 회원가입 시 이메일 중복 검사
     */
    public void isDuplicateMember(Member member){
        Optional<Member> result = memberRepository.findByEmail(member.getEmail());
        if(result.isPresent()){
            throw new IllegalStateException("이미 존재하는 이메일입니다");
        }
    }

}
