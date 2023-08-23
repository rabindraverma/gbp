package com.kk.controller;

import com.kk.dto.GeoResourceDto;
import com.kk.entities.GeoResource;
import com.kk.services.GeoResourceServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class GeoResourceController {

    private static Logger LOGGER = LoggerFactory.getLogger(GeoResourceController.class);
    @Autowired
    private GeoResourceServices geoResourceServices;
    private Long geoId;

    @PostMapping("/georesource")
    public ResponseEntity<GeoResource> saveGeoResouce(@Valid @RequestBody GeoResourceDto geoResourceDto) {
        LOGGER.info("Inside saveGeoResouce() with data  : {} ", geoResourceDto);
        GeoResource response = geoResourceServices.saveGeoResource(geoResourceDto);
        LOGGER.info("Exit from saveGeoResouce() with response  : {} ", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/georesource/{geoId}")
    public ResponseEntity<GeoResource> updateGeoResource(@Valid @PathVariable Long geoId, @RequestBody GeoResourceDto geoResourceDto) {
        LOGGER.info("Inside updateGeoResource() with id  : {} and data : {} ", geoId, geoResourceDto);
        GeoResource geoResource = geoResourceServices.updateGeoResource(geoId, geoResourceDto);
        LOGGER.info("Exit from updateGeoResource() with updated data  : {} ", geoId, geoResource);
        return new ResponseEntity<>(geoResource, HttpStatus.OK);
    }

    @GetMapping("/georesource")
    public ResponseEntity<List<GeoResource>> getAllGeoResource() {
        LOGGER.info("Inside getAllGeoResource() ");
        List<GeoResource> allGeoResource = geoResourceServices.getAllGeoResource();
        LOGGER.info("Exit from getAllGeoResource() with all data : {} ", allGeoResource);
        return new ResponseEntity<>(allGeoResource, HttpStatus.OK);
    }

    @GetMapping("/georesource/{geoId}")
    public ResponseEntity<GeoResource> getGeoResourceById(@PathVariable Long geoId) {
        LOGGER.info("Inside getGeoResourceById() with given id : {} ", geoId);
        GeoResource response = geoResourceServices.getGeoResourceById(geoId);
        LOGGER.info("Exit from getGeoResourceById with data : {} ",response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/georesource/{geoId}")
    public ResponseEntity<GeoResource> deleteGeoResourceById(@PathVariable Long geoId) {
        LOGGER.info("Inside deleteGeoResourceById() with given id : {} ",geoId);
        GeoResource response = geoResourceServices.deleteGeoResource(geoId);
        LOGGER.info("Exit from deleteGeoResourceById() with deleted data : {} ",response);
        return new ResponseEntity<>(response, HttpStatus.GONE);
    }
}
