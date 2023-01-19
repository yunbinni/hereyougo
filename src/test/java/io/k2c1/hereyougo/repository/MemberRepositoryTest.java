package io.k2c1.hereyougo.repository;

import io.k2c1.hereyougo.domain.Address;
import io.k2c1.hereyougo.domain.Member;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void 회원가입(){
//      given
        Member member = 회원생성();

//      when
        Member result = memberRepository.save(member);

//      then
        assertThat(result).usingRecursiveComparison().isEqualTo(member);
    }

    @Test
    public void 회원상세정보(){
//      given
        Member member = 회원생성();

        memberRepository.save(member);

//      when
        Member result = memberRepository.findById(1L).get();

//      then
//        Assertions.assertEquals(member, result);
        assertThat(result).usingRecursiveComparison().isEqualTo(member);
    }


    @Test
    public void 회원이메일존재(){
//      given
        Member member = 회원생성();
        String email = "test@naver.com";
        memberRepository.save(member);

//      when
        Member result = memberRepository.findByEmail(email);

//      then
        assertThat(result).usingRecursiveComparison().isEqualTo(member);
    }

    @Test
    public void 회원이메일존재하지않을경우(){
//      given
        Member member = 회원생성();
        String email = "test2@naver.com";
        boolean isExist;

        memberRepository.save(member);

//      when
        Member result = memberRepository.findByEmail(email);

        if(result != null){
            isExist = true;
        }else{
            isExist = false;
        }

//      then
//        Assertions.assertEquals(false, isExist);
        assertThat(isExist).isEqualTo(false);
    }

    @Test
    public void 회원삭제(){
//      given
        List<Member> members = 회원목록생성();
        Member member = members.get(1);
//      when
        memberRepository.delete(member);

        List<Member> result = memberRepository.findAll();
//      then
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void 로그인(){
//      given
        Member member = 회원생성();
        memberRepository.save(member);
        String email = "test@naver.com";
        String password = "123456";

//      when
        Member result = memberRepository.findByEmailAndPassword(email, password);

//      then
//        Assertions.assertEquals(member.getEmail(), result.getEmail());
        assertThat(result).usingRecursiveComparison().isEqualTo(member);
    }

    public Member 회원생성(){
        Member member = new Member();

        String email = "test@naver.com";
        String password = "123456";
        String nickname = "test";
        String businessType = "요식업";

        member.setEmail(email);
        member.setPassword(password);
        member.setNickname(nickname);
        member.setBusinessType(businessType);

        return member;
    }

    public List<Member> 회원목록생성(){

        List<Member> members = new ArrayList<>();

        for(int i = 0 ; i < 3; i++){
            Member member = new Member();
            String email = "test"+i+"@naver.com";
            String password = "123456"+i;
            String nickname = "test"+i;
            String businessType = "요식업"+i;

            member.setEmail(email);
            member.setPassword(password);
            member.setNickname(nickname);
            member.setBusinessType(businessType);

            memberRepository.save(member);
            
            members.add(member);
        }


        return members;
    }
}