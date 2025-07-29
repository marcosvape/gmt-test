package com.gmt.test.external.dto;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class YouTubeVideoListResponseDTO {
    private String kind;
    private String etag;
    private List<VideoItemDTO> items;
    private PageInfoDTO pageInfo;
}