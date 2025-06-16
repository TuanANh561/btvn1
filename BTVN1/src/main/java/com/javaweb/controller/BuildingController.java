package com.javaweb.controller;

import com.javaweb.controller.response.BuildingDto;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buildings")
public class BuildingController {
    @Autowired
    private BuildingService buildingService;

    @GetMapping
    public List<BuildingDto> getAllBuildings(){
        return buildingService.findAll();
    }

    @GetMapping("/search")
    public List<BuildingDto> searchBuildings(@RequestParam Map<String, Object> params) {
        return buildingService.searchBuildings(params);
    }
}