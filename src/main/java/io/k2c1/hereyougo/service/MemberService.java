package io.k2c1.hereyougo.service;

import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.dto.JoinForm;
import io.k2c1.hereyougo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService
{
    @Autowired
    private final MemberRepository memberRepository;

    public void join(JoinForm joinForm)
    {
        /**
         * TODO : 회원가입 검증로직도 추가해야함
         * 만약 검증이 정상수행? {
         *     JoinForm -> Member 회원으로 변환 // TODO TypeConverter 도입할지 의논하기
         *     회원리포.save(회원)
         *     log.info("New Member Saved");
         *     return;
         * }
         */
    }
}
