package io.k2c1.hereyougo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Image {

    @Id
    private String storedFilename; // UUID

    private String originalFilename;

    @ManyToOne(cascade = CascadeType.PERSIST) @JoinColumn(name = "post_id")
    private Post post;

    public Image(String storedFilename, String originalFilename, Post post) {
        this.storedFilename = storedFilename;
        this.originalFilename = originalFilename;
        this.post = post;

        post.addImage(this);
    }
}
