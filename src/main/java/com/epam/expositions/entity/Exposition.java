package com.epam.expositions.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Exposition implements Persistable<Long>{
    private Long id;
    private Long hostId;
    private String topic;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    private BigDecimal price;
    private Long capacity;
    private String imagePath;
    private String detailsLink;
    private String statusName;
}
