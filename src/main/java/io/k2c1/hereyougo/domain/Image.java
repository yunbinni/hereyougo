package io.k2c1.hereyougo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Image {

    @Id
    private String storedFilename; // UUID

    private String originalFilename;

    @ManyToOne @JoinColumn(name = "post_id")
    private Post post;

    public Image(String storedFilename, String originalFilename, Post post) {
        this.storedFilename = storedFilename;
        this.originalFilename = originalFilename;
        this.post = post;
    }
}
