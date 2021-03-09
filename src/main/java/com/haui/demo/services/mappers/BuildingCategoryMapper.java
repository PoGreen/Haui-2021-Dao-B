package com.haui.demo.services.mappers;

import com.haui.demo.models.entities.BuildingCategory;
import com.haui.demo.models.requests.BuildingCategoryRq;
import com.haui.demo.models.responses.BuildingCategoryRp;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingCategoryMapper {

    public List<BuildingCategoryRp> map(List<BuildingCategory> buildingCategories){
        return buildingCategories.stream().map(t -> {
            BuildingCategoryRp buildingCategoryRp = new BuildingCategoryRp();
            buildingCategoryRp.setId(t.getId());
            buildingCategoryRp.setName(t.getName());
            buildingCategoryRp.setDescription(t.getDescription());
            return buildingCategoryRp;
        }).collect(Collectors.toList());
    }

    public BuildingCategory map(BuildingCategory buildingCategory, BuildingCategoryRq buildingCategoryRq){
        buildingCategory.setName(buildingCategoryRq.getName());
        buildingCategory.setDescription(buildingCategoryRq.getDescription());
        buildingCategory.setBuildingCategory(buildingCategoryRq.getBuildingCategory());
        buildingCategory.setStatus(buildingCategoryRq.getStatus());
        return buildingCategory;
    }

    public BuildingCategoryRp map(BuildingCategory buildingCategory){
        BuildingCategoryRp buildingCategoryRp = new BuildingCategoryRp();
        buildingCategoryRp.setId(buildingCategory.getId());
        buildingCategoryRp.setName(buildingCategory.getName());
        buildingCategoryRp.setDescription(buildingCategory.getDescription());
        return buildingCategoryRp;
    }
}
