package com.epam.expositions.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Exposition {
    private Integer id;
    private String topic;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    private BigDecimal price;
    private String presentationLink;
}
