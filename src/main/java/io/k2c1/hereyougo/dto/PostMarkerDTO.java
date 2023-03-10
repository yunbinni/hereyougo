package io.k2c1.hereyougo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostMarkerDTO {

    @JsonProperty
    private Long Id;
    @JsonProperty
    private String title;
    @JsonProperty
    private String doro;

    public PostMarkerDTO(Long Id, String title, String doro) {
        this.Id = Id;
        this.title = title;
        this.doro = doro;
    }
}
