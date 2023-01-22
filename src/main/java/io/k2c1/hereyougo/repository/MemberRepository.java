package io.k2c1.hereyougo.repository;

import io.k2c1.hereyougo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>
{
    public Member findByEmail(String email);

    public Member findByEmailAndPassword(String email, String password);
}
