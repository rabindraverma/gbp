package com.kk.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@Table(name = "GEO_RESOURCE")
public class GeoResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long geoId;

    @NotNull(message = "Latitude cannot be null")
    private Double latitude;

    @NotNull(message = "Longitude cannot be null")
    private Double longitude;

    @NotNull(message = "City cannot be null")
    private String city;

    @NotNull(message = "Country cannot be null")
    private String country;
}
