package com.kk.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kk.dto.GeoResourceDto;
import com.kk.entities.GeoResource;
import com.kk.exception.GeoResourceNotFoundException;
import com.kk.repositories.GeoResourceRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {GeoResourceServicesImpl.class})
@ExtendWith(SpringExtension.class)
class GeoResourceServicesImplTest {
    @MockBean
    private GeoResourceRepository geoResourceRepository;

    @Autowired
    private GeoResourceServicesImpl geoResourceServicesImpl;

    /**
     * Method under test: {@link GeoResourceServicesImpl#saveGeoResource(GeoResourceDto)}
     */
    @Test
    void testSaveGeoResource() {
        GeoResource geoResource = new GeoResource();
        geoResource.setCity("Oxford");
        geoResource.setCountry("GB");
        geoResource.setGeoId(1L);
        geoResource.setLatitude(10.0d);
        geoResource.setLongitude(10.0d);
        when(geoResourceRepository.save(Mockito.<GeoResource>any())).thenReturn(geoResource);

        GeoResourceDto geoResourceDto = new GeoResourceDto();
        geoResourceDto.setCity("Oxford");
        geoResourceDto.setCountry("GB");
        geoResourceDto.setLatitude(10.0d);
        geoResourceDto.setLongitude(10.0d);
        assertSame(geoResource, geoResourceServicesImpl.saveGeoResource(geoResourceDto));
        verify(geoResourceRepository).save(Mockito.<GeoResource>any());
    }

    /**
     * Method under test: {@link GeoResourceServicesImpl#saveGeoResource(GeoResourceDto)}
     */
    @Test
    void testSaveGeoResource2() {
        when(geoResourceRepository.save(Mockito.<GeoResource>any()))
                .thenThrow(new GeoResourceNotFoundException("An error occurred"));

        GeoResourceDto geoResourceDto = new GeoResourceDto();
        geoResourceDto.setCity("Oxford");
        geoResourceDto.setCountry("GB");
        geoResourceDto.setLatitude(10.0d);
        geoResourceDto.setLongitude(10.0d);
        assertThrows(GeoResourceNotFoundException.class, () -> geoResourceServicesImpl.saveGeoResource(geoResourceDto));
        verify(geoResourceRepository).save(Mockito.<GeoResource>any());
    }

    /**
     * Method under test: {@link GeoResourceServicesImpl#getAllGeoResource()}
     */
    @Test
    void testGetAllGeoResource() {
        ArrayList<GeoResource> geoResourceList = new ArrayList<>();
        when(geoResourceRepository.findAll()).thenReturn(geoResourceList);
        List<GeoResource> actualAllGeoResource = geoResourceServicesImpl.getAllGeoResource();
        assertSame(geoResourceList, actualAllGeoResource);
        assertTrue(actualAllGeoResource.isEmpty());
        verify(geoResourceRepository).findAll();
    }

    /**
     * Method under test: {@link GeoResourceServicesImpl#getAllGeoResource()}
     */
    @Test
    void testGetAllGeoResource2() {
        when(geoResourceRepository.findAll()).thenThrow(new GeoResourceNotFoundException("An error occurred"));
        assertThrows(GeoResourceNotFoundException.class, () -> geoResourceServicesImpl.getAllGeoResource());
        verify(geoResourceRepository).findAll();
    }

    /**
     * Method under test: {@link GeoResourceServicesImpl#getGeoResourceById(long)}
     */
    @Test
    void testGetGeoResourceById() {
        GeoResource geoResource = new GeoResource();
        geoResource.setCity("Oxford");
        geoResource.setCountry("GB");
        geoResource.setGeoId(1L);
        geoResource.setLatitude(10.0d);
        geoResource.setLongitude(10.0d);
        Optional<GeoResource> ofResult = Optional.of(geoResource);
        when(geoResourceRepository.findById(Mockito.<Serializable>any())).thenReturn(ofResult);
        assertSame(geoResource, geoResourceServicesImpl.getGeoResourceById(1L));
        verify(geoResourceRepository).findById(Mockito.<Serializable>any());
    }

    /**
     * Method under test: {@link GeoResourceServicesImpl#getGeoResourceById(long)}
     */
    @Test
    void testGetGeoResourceById2() {
        when(geoResourceRepository.findById(Mockito.<Serializable>any())).thenReturn(Optional.empty());
        assertThrows(GeoResourceNotFoundException.class, () -> geoResourceServicesImpl.getGeoResourceById(1L));
        verify(geoResourceRepository).findById(Mockito.<Serializable>any());
    }

    /**
     * Method under test: {@link GeoResourceServicesImpl#getGeoResourceById(long)}
     */
    @Test
    void testGetGeoResourceById3() {
        when(geoResourceRepository.findById(Mockito.<Serializable>any()))
                .thenThrow(new GeoResourceNotFoundException("An error occurred"));
        assertThrows(GeoResourceNotFoundException.class, () -> geoResourceServicesImpl.getGeoResourceById(1L));
        verify(geoResourceRepository).findById(Mockito.<Serializable>any());
    }

    /**
     * Method under test: {@link GeoResourceServicesImpl#updateGeoResource(long, GeoResourceDto)}
     */
    @Test
    void testUpdateGeoResource() {
        GeoResource geoResource = new GeoResource();
        geoResource.setCity("Oxford");
        geoResource.setCountry("GB");
        geoResource.setGeoId(1L);
        geoResource.setLatitude(10.0d);
        geoResource.setLongitude(10.0d);
        when(geoResourceRepository.save(Mockito.<GeoResource>any())).thenReturn(geoResource);

        GeoResourceDto geoResourceDto = new GeoResourceDto();
        geoResourceDto.setCity("Oxford");
        geoResourceDto.setCountry("GB");
        geoResourceDto.setLatitude(10.0d);
        geoResourceDto.setLongitude(10.0d);
        assertSame(geoResource, geoResourceServicesImpl.updateGeoResource(1L, geoResourceDto));
        verify(geoResourceRepository).save(Mockito.<GeoResource>any());
    }

    /**
     * Method under test: {@link GeoResourceServicesImpl#updateGeoResource(long, GeoResourceDto)}
     */
    @Test
    void testUpdateGeoResource2() {
        when(geoResourceRepository.save(Mockito.<GeoResource>any()))
                .thenThrow(new GeoResourceNotFoundException("An error occurred"));

        GeoResourceDto geoResourceDto = new GeoResourceDto();
        geoResourceDto.setCity("Oxford");
        geoResourceDto.setCountry("GB");
        geoResourceDto.setLatitude(10.0d);
        geoResourceDto.setLongitude(10.0d);
        assertThrows(GeoResourceNotFoundException.class,
                () -> geoResourceServicesImpl.updateGeoResource(1L, geoResourceDto));
        verify(geoResourceRepository).save(Mockito.<GeoResource>any());
    }

    /**
     * Method under test: {@link GeoResourceServicesImpl#deleteGeoResource(long)}
     */
    @Test
    void testDeleteGeoResource() {
        GeoResource geoResource = new GeoResource();
        geoResource.setCity("Oxford");
        geoResource.setCountry("GB");
        geoResource.setGeoId(1L);
        geoResource.setLatitude(10.0d);
        geoResource.setLongitude(10.0d);
        Optional<GeoResource> ofResult = Optional.of(geoResource);
        doNothing().when(geoResourceRepository).deleteById(Mockito.<Serializable>any());
        when(geoResourceRepository.findById(Mockito.<Serializable>any())).thenReturn(ofResult);
        assertSame(geoResource, geoResourceServicesImpl.deleteGeoResource(1L));
        verify(geoResourceRepository).findById(Mockito.<Serializable>any());
        verify(geoResourceRepository).deleteById(Mockito.<Serializable>any());
    }

    /**
     * Method under test: {@link GeoResourceServicesImpl#deleteGeoResource(long)}
     */
    @Test
    void testDeleteGeoResource2() {
        GeoResource geoResource = new GeoResource();
        geoResource.setCity("Oxford");
        geoResource.setCountry("GB");
        geoResource.setGeoId(1L);
        geoResource.setLatitude(10.0d);
        geoResource.setLongitude(10.0d);
        Optional<GeoResource> ofResult = Optional.of(geoResource);
        doThrow(new GeoResourceNotFoundException("An error occurred")).when(geoResourceRepository)
                .deleteById(Mockito.<Serializable>any());
        when(geoResourceRepository.findById(Mockito.<Serializable>any())).thenReturn(ofResult);
        assertThrows(GeoResourceNotFoundException.class, () -> geoResourceServicesImpl.deleteGeoResource(1L));
        verify(geoResourceRepository).findById(Mockito.<Serializable>any());
        verify(geoResourceRepository).deleteById(Mockito.<Serializable>any());
    }

    /**
     * Method under test: {@link GeoResourceServicesImpl#deleteGeoResource(long)}
     */
    @Test
    void testDeleteGeoResource3() {
        when(geoResourceRepository.findById(Mockito.<Serializable>any())).thenReturn(Optional.empty());
        assertThrows(GeoResourceNotFoundException.class, () -> geoResourceServicesImpl.deleteGeoResource(1L));
        verify(geoResourceRepository).findById(Mockito.<Serializable>any());
    }
}

