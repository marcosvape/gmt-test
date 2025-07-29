package com.gmt.test.controller.dto;

import com.gmt.test.model.enuns.Source;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilterDTO {
    private Source source;
}