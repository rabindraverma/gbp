package com.kk.services;

import com.kk.dto.GeoResourceDto;
import com.kk.entities.GeoResource;
import com.kk.exception.GeoResourceNotFoundException;
import com.kk.repositories.GeoResourceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeoResourceServicesImpl implements GeoResourceServices {

    private static Logger LOGGER = LoggerFactory.getLogger(GeoResourceServicesImpl.class);

    @Autowired
    private GeoResourceRepository geoResourceRepo;

    private RedisTemplate redisTemplate;

    @Override
    public GeoResource saveGeoResource(GeoResourceDto geoResourceDto) {
        LOGGER.info("Inside saveGeoResource() with data : {} ", geoResourceDto);
        GeoResource geoResource = new GeoResource();
        BeanUtils.copyProperties(geoResourceDto, geoResource);
        GeoResource saved = geoResourceRepo.save(geoResource);
        LOGGER.info("Exit from saveGeoResource() with saved data : {} ", saved);
        return saved;
    }

    @Override
    public List<GeoResource> getAllGeoResource() {
        LOGGER.info("Inside getAllGeoResource() ");
        List<GeoResource> allGeoResource = geoResourceRepo.findAll();
        LOGGER.info("Exit from getAllGeoResource() with all data : {} ", allGeoResource);
        return allGeoResource;
    }

    @Override
    @Cacheable(value = "geoResource",key = "#geoId")
    public GeoResource getGeoResourceById(long geoId) {
        LOGGER.info("Inside getGeoResourceById() with id : {} ", geoId);
        Optional<GeoResource> byId = geoResourceRepo.findById(geoId);
        if (byId.isPresent()) {
            LOGGER.info("Exit from getGeoResourceById() with id : {} ", geoId);
            return byId.get();
        } else {
            LOGGER.error("GeoResource is not found with given id : {} ", geoId);
            throw new GeoResourceNotFoundException("GeoResource is not found with given id : " + geoId);
        }
    }

    @Override
    @CachePut(value = "geoResource",key = "#geoId")
    public GeoResource updateGeoResource(long geoId, GeoResourceDto geoResourceDto) {
        LOGGER.info("Inside updateGeoResource() with given geoId : {} and data : {} ", geoId, geoResourceDto);
        GeoResource geoResource = new GeoResource();
        geoResource.setGeoId(geoId);
        BeanUtils.copyProperties(geoResourceDto, geoResource);
        GeoResource saved = geoResourceRepo.save(geoResource);
        LOGGER.info("Exit from updateGeoResource() with updated data : {} ", saved);
        return saved;
    }

    @Override
    @CacheEvict(value = "geoResource",allEntries = true)
    public GeoResource deleteGeoResource(long geoId) {
        LOGGER.info("Inside deleteGeoResource() with id : {} ", geoId);
        Optional<GeoResource> optionalGeoResource = geoResourceRepo.findById(geoId);
        if (optionalGeoResource.isPresent()) {
            geoResourceRepo.deleteById(geoId);
            GeoResource geoResource = optionalGeoResource.get();
            LOGGER.info("Exit from deleteGeoResource() with deleted data : {} ", geoResource);
            return geoResource;
        } else {
            LOGGER.error("GeoResource Not found with id : {} ", geoId);
            throw new GeoResourceNotFoundException("GeoResource Not found with id : " + geoId);
        }
    }
}
