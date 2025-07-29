package com.gmt.test.external.dto;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SnippetDTO {
    private String publishedAt;
    private String channelId;
    private String title;
    private String description;
    private ThumbnailsDTO thumbnails;
    private String channelTitle;
    private List<String> tags;
    private String categoryId;
    private String liveBroadcastContent;
    private LocalizedDTO localized;
}