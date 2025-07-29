package com.gmt.test.external.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoItemDTO {
    private String kind;
    private String etag;
    private String id;
    private SnippetDTO snippet;
    private ContentDetailsDTO contentDetails;
}