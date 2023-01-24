package io.k2c1.hereyougo.domain;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;
    private String password;
    private String nickname;
    private String businessType; // 업종

    @OneToMany(mappedBy = "member")
    private List<Appointment> appointments = new ArrayList<>();

    public Member(Long id, String email, String password, String nickname, String businessType) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.businessType = businessType;
    }
}
