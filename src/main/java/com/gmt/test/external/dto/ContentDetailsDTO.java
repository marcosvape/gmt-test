package com.gmt.test.external.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContentDetailsDTO {
    private String duration;
    private String dimension;
    private String definition;
    private String caption;
    private boolean licensedContent;
    private ContentRatingDTO contentRating;
    private String projection;
}