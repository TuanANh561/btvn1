package com.javaweb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity(name = "building")
@NoArgsConstructor
@AllArgsConstructor
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String street;
    private String ward;

    @Column(name = "numberofbasement")
    private Integer numberOfBasement;

    @Column(name = "floorarea")
    private Integer floorArea;

    @Column(name = "rentprice")
    private Integer rentPrice;

    @Column(name = "rentpricedescription")
    private String rentPriceDescription;

    @Column(name = "servicefee")
    private String serviceFee;

    @Column(name = "brokeragefee")
    private BigDecimal brokerageFee;

    @Column(name = "managername")
    private String managerName;

    @Column(name = "managerphonenumber")
    private String managerPhoneNumber;

    @ManyToOne
    @JoinColumn(name = "districtid")
    private District district;

    @OneToMany(mappedBy = "building")
    private List<BuildingRentType> buildingRentTypes;

    @OneToMany(mappedBy = "building")
    private List<RentArea> rentAreas;

    @OneToMany(mappedBy = "building")
    private List<AssignmentBuilding> assignmentBuildings;
}
