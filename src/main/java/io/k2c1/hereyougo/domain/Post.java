package io.k2c1.hereyougo.domain;

import io.k2c1.hereyougo.file.UploadFile;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter @Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
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

    @Embedded
    private List<String> imagesFilepath;

    public void plusViews() {
        this.views++;
    }
}
