package com.epam.expositions.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exposition implements Persistable<Long>{
    private Long id;
    private String topic;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    private BigDecimal price;
    private String presentationLink;
}
