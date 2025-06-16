package com.javaweb.service;

import com.javaweb.controller.response.BuildingDto;
import com.javaweb.entity.Building;

import java.util.List;
import java.util.Map;

public interface BuildingService {
    List<BuildingDto> findAll();
    List<BuildingDto> searchBuildings(Map<String, Object> params);
}