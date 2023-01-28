package io.k2c1.hereyougo.service;

import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.dto.LoginForm;
import io.k2c1.hereyougo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService
{
    @Autowired
    private final MemberRepository memberRepository;

    public Member login(LoginForm loginForm) {
        return memberRepository.findByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());
    }
}
