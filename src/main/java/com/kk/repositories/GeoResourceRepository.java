package com.kk.repositories;

import com.kk.entities.GeoResource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface GeoResourceRepository extends JpaRepository<GeoResource, Serializable> {
}
