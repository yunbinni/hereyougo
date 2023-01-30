package io.k2c1.hereyougo.repository;

import io.k2c1.hereyougo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>
{
    public Optional<Member> findByEmail(String email);

    public Member findByEmailAndPassword(String email, String password);
}
