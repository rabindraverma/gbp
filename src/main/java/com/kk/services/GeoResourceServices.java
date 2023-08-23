package com.kk.services;

import com.kk.dto.GeoResourceDto;
import com.kk.entities.GeoResource;

import java.util.List;

public interface GeoResourceServices {

    public GeoResource saveGeoResource(GeoResourceDto geoResourceDto);

    public List<GeoResource> getAllGeoResource();

    public GeoResource getGeoResourceById(long geoId);

    public GeoResource updateGeoResource(long geoId, GeoResourceDto geoResourceDto);

    public GeoResource deleteGeoResource(long geoId);
}
