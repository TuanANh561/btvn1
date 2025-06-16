package com.javaweb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "buildingrenttype")
@NoArgsConstructor
@AllArgsConstructor
public class BuildingRentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "buildingid")
    private Building building;

    @ManyToOne
    @JoinColumn(name = "renttypeid")
    private RentType rentType;
}
