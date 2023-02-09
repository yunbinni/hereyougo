package io.k2c1.hereyougo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member writer; // 작성자

    private String title;
    private String content;
    private String size;

    @ColumnDefault("0")
    private int views; // 조회수

    private Integer quantity; // 남은 수량

    @Embedded
    private Address address;

    private LocalDateTime timestamp; // 작성일/시각

    public void plusViews() {
        this.views++;
    }
}
