package com.javaweb.repository.impl;

import com.javaweb.entity.Building;
import com.javaweb.repository.BuildingRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Building> findAll() {
        return entityManager.createQuery("SELECT b FROM building b JOIN b.district", Building.class).getResultList();
    }

    @Override
    public List<Building> searchBuildings(Map<String, Object> params) {
        StringBuilder jpql = new StringBuilder("SELECT b FROM building b JOIN b.district d " +
                "LEFT JOIN b.rentAreas ra " +
                "LEFT JOIN b.buildingRentTypes brt LEFT JOIN brt.rentType " +
                "LEFT JOIN b.assignmentBuildings ab WHERE 1=1");
        List<String> paramNames = new ArrayList<>();
        List<Object> paramValues = new ArrayList<>();

        if (params.containsKey("name")) {
            jpql.append(" AND b.name LIKE :name");
            paramNames.add("name");
            paramValues.add("%" + params.get("name").toString() + "%");
        }
        if (params.containsKey("ward")) {
            jpql.append(" AND b.ward LIKE :ward");
            paramNames.add("ward");
            paramValues.add("%" + params.get("ward").toString() + "%");
        }
        if (params.containsKey("street")) {
            jpql.append(" AND b.street LIKE :street");
            paramNames.add("street");
            paramValues.add("%" + params.get("street").toString() + "%");
        }
        if (params.containsKey("districtId")) {
            jpql.append(" AND b.district.id = :districtId");
            paramNames.add("districtId");
            paramValues.add(Long.valueOf(params.get("districtId").toString()));
        }
        if (params.containsKey("numberOfBasement")) {
            jpql.append(" AND b.numberOfBasement = :numberOfBasement");
            paramNames.add("numberOfBasement");
            paramValues.add(Integer.valueOf(params.get("numberOfBasement").toString()));
        }
        if (params.containsKey("floorArea")) {
            jpql.append(" AND b.floorArea = :floorArea");
            paramNames.add("floorArea");
            paramValues.add(Integer.valueOf(params.get("floorArea").toString()));
        }
        if (params.containsKey("rentPriceFrom")) {
            jpql.append(" AND b.rentPrice >= :rentPriceFrom");
            paramNames.add("rentPriceFrom");
            paramValues.add(Integer.valueOf(params.get("rentPriceFrom").toString()));
        }
        if (params.containsKey("rentPriceTo")) {
            jpql.append(" AND b.rentPrice <= :rentPriceTo");
            paramNames.add("rentPriceTo");
            paramValues.add(Integer.valueOf(params.get("rentPriceTo").toString()));
        }

        if (params.containsKey("rentPriceDescription")) {
            jpql.append(" AND b.rentPriceDescription LIKE :rentPriceDescription");
            paramNames.add("rentPriceDescription");
            paramValues.add("%" + params.get("rentPriceDescription").toString() + "%");
        }
        if (params.containsKey("serviceFee")) {
            jpql.append(" AND b.serviceFee LIKE :serviceFee");
            paramNames.add("serviceFee");
            paramValues.add("%" + params.get("serviceFee").toString() + "%");
        }
        if (params.containsKey("managerName")) {
            jpql.append(" AND b.managerName LIKE :managerName");
            paramNames.add("managerName");
            paramValues.add("%" + params.get("managerName").toString() + "%");
        }
        if (params.containsKey("managerPhoneNumber")) {
            jpql.append(" AND b.managerPhoneNumber LIKE :managerPhoneNumber");
            paramNames.add("managerPhoneNumber");
            paramValues.add("%" + params.get("managerPhoneNumber").toString() + "%");
        }
        if (params.containsKey("type")) {
            jpql.append(" AND EXISTS (SELECT 1 FROM b.buildingRentTypes brt JOIN brt.rentType rt WHERE rt.code IN (:type))");
            paramNames.add("type");
            paramValues.add(List.of(params.get("type").toString().split(",")));
        }
        if (params.containsKey("staffId")) {
            jpql.append(" AND EXISTS (SELECT 1 FROM b.assignmentBuildings ab WHERE ab.staff.id = :staffId)");
            paramNames.add("staffId");
            paramValues.add(Long.valueOf(params.get("staffId").toString()));
        }
        if (params.containsKey("areaFrom")) {
            jpql.append(" AND EXISTS (SELECT 1 FROM b.rentAreas ra WHERE ra.value >= :areaFrom)");
            paramNames.add("areaFrom");
            paramValues.add(Integer.valueOf(params.get("areaFrom").toString()));
        }
        if (params.containsKey("areaTo")) {
            jpql.append(" AND EXISTS (SELECT 1 FROM b.rentAreas ra WHERE ra.value <= :areaTo)");
            paramNames.add("areaTo");
            paramValues.add(Integer.valueOf(params.get("areaTo").toString()));
        }

        Query query = entityManager.createQuery(jpql.toString(), Building.class);
        for (int i = 0; i < paramNames.size(); i++) {
            query.setParameter(paramNames.get(i), paramValues.get(i));
        }

        return query.getResultList();
    }

//    @Override
//    public List<Building> searchBuildings(Map<String, Object> params) {
//        StringBuilder jpql = new StringBuilder("SELECT b FROM building b WHERE 1=1");
//        List<Object> parameters = new ArrayList<>();
//        int paramIndex = 1;
//
//        if (params.containsKey("name")) {
//            jpql.append(" AND b.name LIKE ?").append(paramIndex);
//            parameters.add("%" + params.get("name").toString() + "%");
//            paramIndex++;
//        }
//        if (params.containsKey("ward")) {
//            jpql.append(" AND b.ward LIKE ?").append(paramIndex);
//            parameters.add("%" + params.get("ward").toString() + "%");
//            paramIndex++;
//        }
//        if (params.containsKey("street")) {
//            jpql.append(" AND b.street LIKE ?").append(paramIndex);
//            parameters.add("%" + params.get("street").toString() + "%");
//            paramIndex++;
//        }
//        if (params.containsKey("districtId")) {
//            jpql.append(" AND b.district.id = ?").append(paramIndex);
//            parameters.add(Long.valueOf(params.get("districtId").toString()));
//            paramIndex++;
//        }
//        if (params.containsKey("numberOfBasement")) {
//            jpql.append(" AND b.numberOfBasement = ?").append(paramIndex);
//            parameters.add(Integer.valueOf(params.get("numberOfBasement").toString()));
//            paramIndex++;
//        }
//        if (params.containsKey("floorArea")) {
//            jpql.append(" AND b.floorArea = ?").append(paramIndex);
//            parameters.add(Integer.valueOf(params.get("floorArea").toString()));
//            paramIndex++;
//        }
//        if (params.containsKey("rentPrice")) {
//            jpql.append(" AND b.rentPrice = ?").append(paramIndex);
//            parameters.add(Integer.valueOf(params.get("rentPrice").toString()));
//            paramIndex++;
//        }
//        if (params.containsKey("rentPriceDescription")) {
//            jpql.append(" AND b.rentPriceDescription LIKE ?").append(paramIndex);
//            parameters.add("%" + params.get("rentPriceDescription").toString() + "%");
//            paramIndex++;
//        }
//        if (params.containsKey("serviceFee")) {
//            jpql.append(" AND b.serviceFee LIKE ?").append(paramIndex);
//            parameters.add("%" + params.get("serviceFee").toString() + "%");
//            paramIndex++;
//        }
//        if (params.containsKey("managerName")) {
//            jpql.append(" AND b.managerName LIKE ?").append(paramIndex);
//            parameters.add("%" + params.get("managerName").toString() + "%");
//            paramIndex++;
//        }
//        if (params.containsKey("managerPhoneNumber")) {
//            jpql.append(" AND b.managerPhoneNumber LIKE ?").append(paramIndex);
//            parameters.add("%" + params.get("managerPhoneNumber").toString() + "%");
//            paramIndex++;
//        }
//        if (params.containsKey("type")) {
//            jpql.append(" AND EXISTS (SELECT 1 FROM b.buildingRentTypes brt JOIN brt.renttype rt WHERE rt.code IN (?").append(paramIndex).append("))");
//            parameters.add(List.of(params.get("type").toString().split(",")));
//            paramIndex++;
//        }
//        if (params.containsKey("staffId")) {
//            jpql.append(" AND EXISTS (SELECT 1 FROM b.assignmentBuildings ab WHERE ab.staff.id = ?").append(paramIndex).append(")");
//            parameters.add(Long.valueOf(params.get("staffId").toString()));
//            paramIndex++;
//        }
//        if (params.containsKey("areaFrom")) {
//            jpql.append(" AND EXISTS (SELECT 1 FROM b.rentAreas ra WHERE ra.value >= ?").append(paramIndex).append(")");
//            parameters.add(Integer.valueOf(params.get("areaFrom").toString()));
//            paramIndex++;
//        }
//        if (params.containsKey("areaTo")) {
//            jpql.append(" AND EXISTS (SELECT 1 FROM b.rentAreas ra WHERE ra.value <= ?").append(paramIndex).append(")");
//            parameters.add(Integer.valueOf(params.get("areaTo").toString()));
//        }
//
//        Query query = entityManager.createQuery(jpql.toString(), Building.class);
//        for (int i = 0; i < parameters.size(); i++) {
//            query.setParameter(i + 1, parameters.get(i));
//        }
//
//        return query.getResultList();
//    }

//    @Override
//    public List<Building> searchBuildings(Map<String, Object> params) {
//        StringBuilder jpql = new StringBuilder("SELECT b FROM building b WHERE 1=1");
//        List<String> paramNames = new ArrayList<>();
//        List<Object> paramValues = new ArrayList<>();
//
//        if (params.containsKey("name")) {
//            jpql.append(" AND b.name LIKE :name");
//            paramNames.add("name");
//            paramValues.add("%" + params.get("name").toString() + "%");
//        }
//        if (params.containsKey("ward")) {
//            jpql.append(" AND b.ward LIKE :ward");
//            paramNames.add("ward");
//            paramValues.add("%" + params.get("ward").toString() + "%");
//        }
//        if (params.containsKey("street")) {
//            jpql.append(" AND b.street LIKE :street");
//            paramNames.add("street");
//            paramValues.add("%" + params.get("street").toString() + "%");
//        }
//        if (params.containsKey("districtId")) {
//            jpql.append(" AND b.district.id = :districtId");
//            paramNames.add("districtId");
//            paramValues.add(Long.valueOf(params.get("districtId").toString()));
//        }
//        if (params.containsKey("numberOfBasement")) {
//            jpql.append(" AND b.numberOfBasement = :numberOfBasement");
//            paramNames.add("numberOfBasement");
//            paramValues.add(Integer.valueOf(params.get("numberOfBasement").toString()));
//        }
//        if (params.containsKey("floorArea")) {
//            jpql.append(" AND b.floorArea = :floorArea");
//            paramNames.add("floorArea");
//            paramValues.add(Integer.valueOf(params.get("floorArea").toString()));
//        }
//        if (params.containsKey("rentPrice")) {
//            jpql.append(" AND b.rentPrice = :rentPrice");
//            paramNames.add("rentPrice");
//            paramValues.add(Integer.valueOf(params.get("rentPrice").toString()));
//        }
//        if (params.containsKey("rentPriceDescription")) {
//            jpql.append(" AND b.rentPriceDescription LIKE :rentPriceDescription");
//            paramNames.add("rentPriceDescription");
//            paramValues.add("%" + params.get("rentPriceDescription").toString() + "%");
//        }
//        if (params.containsKey("serviceFee")) {
//            jpql.append(" AND b.serviceFee LIKE :serviceFee");
//            paramNames.add("serviceFee");
//            paramValues.add("%" + params.get("serviceFee").toString() + "%");
//        }
//        if (params.containsKey("managerName")) {
//            jpql.append(" AND b.managerName LIKE :managerName");
//            paramNames.add("managerName");
//            paramValues.add("%" + params.get("managerName").toString() + "%");
//        }
//        if (params.containsKey("managerPhoneNumber")) {
//            jpql.append(" AND b.managerPhoneNumber LIKE :managerPhoneNumber");
//            paramNames.add("managerPhoneNumber");
//            paramValues.add("%" + params.get("managerPhoneNumber").toString() + "%");
//        }
//        if (params.containsKey("type")) {
//            jpql.append(" AND EXISTS (SELECT 1 FROM b.buildingRentTypes brt JOIN brt.renttype rt WHERE rt.code IN (:type))");
//            paramNames.add("type");
//            paramValues.add(List.of(params.get("type").toString().split(",")));
//        }
//        if (params.containsKey("staffId")) {
//            jpql.append(" AND EXISTS (SELECT 1 FROM b.assignmentBuildings ab WHERE ab.staff.id = :staffId)");
//            paramNames.add("staffId");
//            paramValues.add(Long.valueOf(params.get("staffId").toString()));
//        }
//        if (params.containsKey("areaFrom")) {
//            jpql.append(" AND EXISTS (SELECT 1 FROM b.rentAreas ra WHERE ra.value >= :areaFrom)");
//            paramNames.add("areaFrom");
//            paramValues.add(Integer.valueOf(params.get("areaFrom").toString()));
//        }
//        if (params.containsKey("areaTo")) {
//            jpql.append(" AND EXISTS (SELECT 1 FROM b.rentAreas ra WHERE ra.value <= :areaTo)");
//            paramNames.add("areaTo");
//            paramValues.add(Integer.valueOf(params.get("areaTo").toString()));
//        }
//
//        Query query = entityManager.createQuery(jpql.toString(), Building.class);
//        for (int i = 0; i < paramNames.size(); i++) {
//            query.setParameter(paramNames.get(i), paramValues.get(i));
//        }
//
//        return query.getResultList();
//    }
}