package com.javaweb.repository;
import com.javaweb.entity.Building;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BuildingRepository {
    List<Building> findAll();
    List<Building> searchBuildings(Map<String, Object> params);
}