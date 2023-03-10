package io.k2c1.hereyougo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table(name = "FavoriteCategory")
public class FavoriteCategory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favoriteCategoryId")
    private Long id;

    private String categoryName;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
