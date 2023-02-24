package io.k2c1.hereyougo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Image {

    @Id
    private String storedFilename; // UUID

    private String originalFilename;

    @ManyToOne(cascade = CascadeType.PERSIST) @JoinColumn(name = "post_id")
    private Post post;
}
