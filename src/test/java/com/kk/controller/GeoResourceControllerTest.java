package com.kk.controller;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kk.dto.GeoResourceDto;
import com.kk.entities.GeoResource;
import com.kk.services.GeoResourceServices;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {GeoResourceController.class})
@ExtendWith(SpringExtension.class)
class GeoResourceControllerTest {
    @Autowired
    private GeoResourceController geoResourceController;

    @MockBean
    private GeoResourceServices geoResourceServices;

    /**
     * Method under test: {@link GeoResourceController#updateGeoResource(Long, GeoResourceDto)}
     */
    @Test
    void testUpdateGeoResource() throws Exception {
        GeoResource geoResource = new GeoResource();
        geoResource.setCity("Oxford");
        geoResource.setCountry("GB");
        geoResource.setGeoId(1L);
        geoResource.setLatitude(10.0d);
        geoResource.setLongitude(10.0d);
        when(geoResourceServices.updateGeoResource(anyLong(), Mockito.<GeoResourceDto>any())).thenReturn(geoResource);

        GeoResourceDto geoResourceDto = new GeoResourceDto();
        geoResourceDto.setCity("Oxford");
        geoResourceDto.setCountry("GB");
        geoResourceDto.setLatitude(10.0d);
        geoResourceDto.setLongitude(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(geoResourceDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/georesource/{geoId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(geoResourceController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"geoId\":1,\"latitude\":10.0,\"longitude\":10.0,\"city\":\"Oxford\",\"country\":\"GB\"}"));
    }

    /**
     * Method under test: {@link GeoResourceController#deleteGeoResourceById(Long)}
     */
    @Test
    void testDeleteGeoResourceById() throws Exception {
        GeoResource geoResource = new GeoResource();
        geoResource.setCity("Oxford");
        geoResource.setCountry("GB");
        geoResource.setGeoId(1L);
        geoResource.setLatitude(10.0d);
        geoResource.setLongitude(10.0d);
        when(geoResourceServices.deleteGeoResource(anyLong())).thenReturn(geoResource);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/georesource/{geoId}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(geoResourceController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(410))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"geoId\":1,\"latitude\":10.0,\"longitude\":10.0,\"city\":\"Oxford\",\"country\":\"GB\"}"));
    }

    /**
     * Method under test: {@link GeoResourceController#getAllGeoResource()}
     */
    @Test
    void testGetAllGeoResource() throws Exception {
        when(geoResourceServices.getAllGeoResource()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/georesource");
        MockMvcBuilders.standaloneSetup(geoResourceController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link GeoResourceController#getAllGeoResource()}
     */
    @Test
    void testGetAllGeoResource2() throws Exception {
        when(geoResourceServices.getAllGeoResource()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/georesource");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(geoResourceController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link GeoResourceController#getGeoResourceById(Long)}
     */
    @Test
    void testGetGeoResourceById() throws Exception {
        GeoResource geoResource = new GeoResource();
        geoResource.setCity("Oxford");
        geoResource.setCountry("GB");
        geoResource.setGeoId(1L);
        geoResource.setLatitude(10.0d);
        geoResource.setLongitude(10.0d);
        when(geoResourceServices.getGeoResourceById(anyLong())).thenReturn(geoResource);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/georesource/{geoId}", 1L);
        MockMvcBuilders.standaloneSetup(geoResourceController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"geoId\":1,\"latitude\":10.0,\"longitude\":10.0,\"city\":\"Oxford\",\"country\":\"GB\"}"));
    }

    /**
     * Method under test: {@link GeoResourceController#saveGeoResouce(GeoResourceDto)}
     */
    @Test
    void testSaveGeoResouce() throws Exception {
        GeoResource geoResource = new GeoResource();
        geoResource.setCity("Oxford");
        geoResource.setCountry("GB");
        geoResource.setGeoId(1L);
        geoResource.setLatitude(10.0d);
        geoResource.setLongitude(10.0d);
        when(geoResourceServices.saveGeoResource(Mockito.<GeoResourceDto>any())).thenReturn(geoResource);

        GeoResourceDto geoResourceDto = new GeoResourceDto();
        geoResourceDto.setCity("Oxford");
        geoResourceDto.setCountry("GB");
        geoResourceDto.setLatitude(10.0d);
        geoResourceDto.setLongitude(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(geoResourceDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/georesource")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(geoResourceController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"geoId\":1,\"latitude\":10.0,\"longitude\":10.0,\"city\":\"Oxford\",\"country\":\"GB\"}"));
    }
}

