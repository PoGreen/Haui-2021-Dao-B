package com.haui.demo.services.mappers;

import com.haui.demo.models.entities.Building;
import com.haui.demo.models.entities.BuildingCategory;
import com.haui.demo.models.requests.BuildingRq;
import com.haui.demo.models.responses.BuildingDetailRp;
import com.haui.demo.models.responses.BuildingRp;
import com.haui.demo.repositories.BuildingCategoryRepository;
import com.haui.demo.services.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingMapper {

    @Autowired
    private ILocationService locationService;

    @Autowired
    private BuildingCategoryRepository buildingCategoryRepository;

    public BuildingRp map(Building building) {
        BuildingRp rp = new BuildingRp();
        rp.setName(building.getName());
        rp.setTitle(building.getTitle());
        rp.setId(building.getId());
        rp.setAddress(locationService.getLocationByWard(building.getWard()) + building.getAddress());
        rp.setBedroom(building.getBedroom());
        rp.setFunctionRoom(building.getFunctionRoom());
        rp.setPrice(building.getPrice());
        rp.setFloorArea(building.getFloorArea());
        rp.setSaleRent(building.getSaleRent());
        rp.setStatus(building.getStatus());
        return rp;
    }

    public Page<BuildingRp> map(Page<Building> buildings) {
        return buildings.map(this::map);
    }

    public List<BuildingRp> map(List<Building> buildings) {
        return buildings.stream().map(this::map).collect(Collectors.toList());
    }

    public Building map(Building building, BuildingRq buildingRq) {
        building.setName(buildingRq.getName());
        building.setDescription(buildingRq.getDescription());
        building.setCarPark(buildingRq.getCarPark());
        building.setMotoPark(buildingRq.getMotoPark());
        building.setFloorArea(buildingRq.getFloorArea());
        building.setHomeFrontage(buildingRq.getHomeFrontage());
        building.setTitle(buildingRq.getTitle());
        building.setNumberFloor(buildingRq.getNumberFloor());
        building.setBedroom(buildingRq.getBedroom());
        building.setFunctionRoom(buildingRq.getFunctionRoom());
        building.setAltarRoom(buildingRq.getAltarRoom());
        building.setPrice(buildingRq.getPrice());
        building.setCampusArea(buildingRq.getCampusArea());
        building.setDirection(buildingRq.getDirection());
        building.setMap(building.getMap());
        building.setElectricityPrice(buildingRq.getElectricityPrice());
        building.setFrequence(buildingRq.getFrequence());
        building.setWaterPrice(buildingRq.getWaterPrice());
        building.setServicePrice(buildingRq.getServicePrice());
        building.setHomeDeposit(buildingRq.getHomeDeposit());
        building.setAddress(buildingRq.getAddress());
        building.setWard(building.getWard());
        building.setSaleRent(buildingRq.getSaleRent());
        building.setServicePrice(buildingRq.getServicePrice());
        building.setBuildingCategory(buildingRq.getBuildingCategory());
        building.setWard(buildingRq.getWard());
        return building;
    }

    public BuildingDetailRp maps(Building building) {
        BuildingDetailRp buildingDetailRp = new BuildingDetailRp();
        buildingDetailRp.setId(building.getId());
        buildingDetailRp.setName(building.getName());
        buildingDetailRp.setDescription(building.getDescription());
        buildingDetailRp.setCarPark(building.getCarPark());
        buildingDetailRp.setMotoPark(building.getMotoPark());
        buildingDetailRp.setFloorArea(building.getFloorArea());
        buildingDetailRp.setHomeFrontage(building.getHomeFrontage());
        buildingDetailRp.setNumberFloor(building.getNumberFloor());
        buildingDetailRp.setTitle(building.getTitle());
        buildingDetailRp.setBedroom(building.getBedroom());
        buildingDetailRp.setFunctionRoom(building.getFunctionRoom());
        buildingDetailRp.setAltarRoom(building.getAltarRoom());
        buildingDetailRp.setPrice(building.getPrice());
        buildingDetailRp.setCampusArea(building.getCampusArea());
        buildingDetailRp.setDirection(building.getDirection());
        buildingDetailRp.setMap(building.getMap());
        buildingDetailRp.setElectricityPrice(building.getElectricityPrice());
        buildingDetailRp.setFrequence(building.getFrequence());
        buildingDetailRp.setWaterPrice(building.getWaterPrice());
        buildingDetailRp.setServicePrice(building.getServicePrice());
        buildingDetailRp.setHomeDeposit(building.getHomeDeposit());
        buildingDetailRp.setAddress(locationService.getLocationByWard(building.getWard()) + building.getAddress());
        buildingDetailRp.setWard(building.getWard());
        buildingDetailRp.setSaleRent(building.getSaleRent());
        buildingDetailRp.setServicePrice(building.getServicePrice());

        BuildingCategory buildingCategory = buildingCategoryRepository.findById(building.getBuildingCategory()).orElse(new BuildingCategory());
        buildingDetailRp.setBuildingCategory(buildingCategory.getName());
        buildingDetailRp.setWard(building.getWard());
        return buildingDetailRp;
    }

    public List<BuildingDetailRp> mapToBuildingDetailRps(List<Building> buildings){
        return buildings.stream().map(this::maps).collect(Collectors.toList());
    }
}
