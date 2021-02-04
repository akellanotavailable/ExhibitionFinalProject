package com.epam.expositions.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exposition {
    private Integer id;
    private String topic;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    private BigDecimal price;
    private String presentationLink;
}
