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

    private Integer recommend; // 추천 수

    @Embedded
    private Address address;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private LocalDateTime timestamp; // 작성일/시각

    public void plusViews() {
        this.views++;
    }

    public Post(Member writer, String title, String content, String size, int views, Integer quantity, Integer recommend, Address address, LocalDateTime timestamp) {
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.size = size;
        this.views = views;
        this.quantity = quantity;
        this.recommend = recommend;
        this.address = address;
        this.timestamp = timestamp;
    }
}
