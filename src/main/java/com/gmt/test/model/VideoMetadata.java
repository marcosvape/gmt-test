package com.gmt.test.model;

import com.gmt.test.model.enuns.Source;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoMetadata {
    private Integer id;
    private String sourceId;
    private String title;
    private String description;
    private Source source;
    private String duration;
    private String uploadedAt;
}