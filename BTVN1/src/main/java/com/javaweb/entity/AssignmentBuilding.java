package com.javaweb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "assignmentbuilding")
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentBuilding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "staffid")
    private User staff;

    @ManyToOne
    @JoinColumn(name = "buildingid")
    private Building building;
}
