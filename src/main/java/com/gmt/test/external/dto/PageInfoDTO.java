package com.gmt.test.external.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageInfoDTO {
    private int totalResults;
    private int resultsPerPage;
}