package com.itheima.domain.Entity;

import lombok.Data;

@Data
public class CarImage {
    private Long carImageId;

    private Long carId;

    private String imageAddress;
}
