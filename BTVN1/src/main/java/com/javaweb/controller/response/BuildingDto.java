package com.javaweb.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuildingDto {
    private Long id;
    private String name;
    private Address address;
    private Integer numberOfBasement;
    private Integer floorArea;
    private Integer rentPrice;
    private String rentPriceDescription;
    private String serviceFee;
    private BigDecimal brokerageFee;
    private String managerName;
    private String managerPhoneNumber;
    private String rentArea;
    private String type;
}
