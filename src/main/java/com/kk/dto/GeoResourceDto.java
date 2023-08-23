package com.kk.dto;

import lombok.Data;

@Data
public class GeoResourceDto {

    private Double latitude;

    private Double longitude;

    private String city;

    private String country;
}
