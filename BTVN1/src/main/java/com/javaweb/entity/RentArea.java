package com.javaweb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "rentarea")
@NoArgsConstructor
@AllArgsConstructor
public class RentArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer value;
    @ManyToOne
    @JoinColumn(name = "buildingid")
    private Building building;


}
