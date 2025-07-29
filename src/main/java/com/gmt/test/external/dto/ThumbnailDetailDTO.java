package com.gmt.test.external.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ThumbnailDetailDTO {
    private String url;
    private int width;
    private int height;
}