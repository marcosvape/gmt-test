package com.gmt.test.controller.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoMetadataStatsDTO {
    private String totalVideos;
    private String source;
    private String averageDuration;
}