package io.k2c1.hereyougo.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
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

    private Integer width;
    private Integer depth;
    private Integer height;

    @ColumnDefault("0")
    private int views; // 조회수

    private String address;

    private LocalDateTime timestamp; // 작성일/시각
}
