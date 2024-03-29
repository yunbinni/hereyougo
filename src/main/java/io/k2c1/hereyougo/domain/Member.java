package io.k2c1.hereyougo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "member")
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;
    private String password;
    private String nickname;

    @OneToOne
    private Category category;

    @Embedded
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "wanted")
    private List<Appointment> appointments = new ArrayList<>();

    /***
     * RDB에서는 컬렉션과 같은 데이터를 컬럼에 저장할 수 없어서
     * 별도의 테이블을 생성(@ElementCollection)하거나 Converter를 만들어야된다
     * 리스트 삭제 같은 경우, 성능저하가 생길수도..?일단 구현 후 어려운점 있으면
     * 테이블로 바꾸는걸로
     */
//    private List<String> keywords;

    public Member(Long id, String email, String password, String nickname, Category category) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.category = category;
    }

    public void updateMemberInfo(String password, String nickname, Category category){
        this.password= password;
        this.nickname = nickname;
        this.category = category;
    }

    public Member(Long id, String email, String password, String nickname, Category category, Address address) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.category = category;
        this.address = address;
    }

    public Member(String email, String password, String nickname, Category category, Address address) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.category = category;
        this.address = address;
    }

    public Member(String email, String password, String nickname, Address address) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.address = address;
    }
}
