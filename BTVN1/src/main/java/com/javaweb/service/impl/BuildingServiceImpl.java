package com.javaweb.service.impl;

import com.javaweb.controller.response.Address;
import com.javaweb.controller.response.BuildingDto;
import com.javaweb.entity.Building;
import com.javaweb.entity.RentArea;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.BuildingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<BuildingDto> findAll() {
        List<Building> buildings = buildingRepository.findAll();
        return buildings.stream().map(building -> {
            BuildingDto buildingDto = modelMapper.map(building, BuildingDto.class);
            Address address = new Address();
                address.setWard(building.getWard());
                address.setStreet(building.getStreet());
                address.setDistrict(building.getDistrict().getName());
            buildingDto.setAddress(address);

            String rentAreas = building.getRentAreas().stream()
                    .map(RentArea::getValue)
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
            buildingDto.setRentArea(rentAreas.isEmpty() ? null : rentAreas);

            String type = building.getBuildingRentTypes().stream()
                    .map(brt -> brt.getRentType().getCode())
                    .collect(Collectors.joining(", "));
            buildingDto.setType(type.isEmpty() ? null : type);

            return buildingDto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<BuildingDto> searchBuildings(Map<String, Object> params) {
        List<Building> buildings = buildingRepository.searchBuildings(params);
        return buildings.stream().map(building -> {
            BuildingDto buildingDto = modelMapper.map(building, BuildingDto.class);
            Address address = new Address();
                address.setWard(building.getWard());
                address.setStreet(building.getStreet());
                address.setDistrict(building.getDistrict().getName());
            buildingDto.setAddress(address);

            String rentAreas = building.getRentAreas().stream()
                    .map(RentArea::getValue)
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
            buildingDto.setRentArea(rentAreas.isEmpty() ? null : rentAreas);

            String type = building.getBuildingRentTypes().stream()
                    .map(brt -> brt.getRentType().getCode())
                    .collect(Collectors.joining(", "));
            buildingDto.setType(type.isEmpty() ? null : type);

            return buildingDto;
        }).collect(Collectors.toList());
    }
}