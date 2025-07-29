package com.gmt.test.external.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThumbnailsDTO {
    @JsonProperty("default")
    private ThumbnailDetailDTO defaultThumbnail;
    private ThumbnailDetailDTO medium;
    private ThumbnailDetailDTO high;
    private ThumbnailDetailDTO standard;
    private ThumbnailDetailDTO maxres;
}