package com.gmt.test.external.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LocalizedDTO {
    private String title;
    private String description;
}