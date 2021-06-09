package com.haui.demo.services.impls;

import com.haui.demo.models.bos.Response;
import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.models.entities.BuildingCategory;
import com.haui.demo.models.entities.User;
import com.haui.demo.models.requests.BuildingCategoryRq;
import com.haui.demo.models.requests.StatusRq;
import com.haui.demo.models.responses.BuildingCategoryRp;
import com.haui.demo.repositories.BuildingCategoryRepository;
import com.haui.demo.services.IBuildingCategoryService;
import com.haui.demo.services.JwtUser;
import com.haui.demo.services.mappers.BuildingCategoryMapper;
import com.haui.demo.utils.Global;
import com.haui.demo.utils.StringResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class BuildingCategoryService implements IBuildingCategoryService {

    @Autowired
    private BuildingCategoryRepository buildingCategoryRepository;
    @Autowired
    private BuildingCategoryMapper mapper;
    @Autowired
    private JwtUser jwtUser;

    @Override
    public ResponseEntity<SystemResponse<Object>> getOne(HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<SystemResponse<Object>> getAll(Integer status) {
        List<BuildingCategory> buildingCategories = new ArrayList<>();
        switch (status) {
            case 1:
                buildingCategories = buildingCategoryRepository.findByStatus(Global.ACTIVE);
                break;
            case 0:
                buildingCategories = buildingCategoryRepository.findByStatus(Global.NOACTIVE);
                break;
            default:
                buildingCategories = buildingCategoryRepository.findAll();
        }
        List<BuildingCategoryRp> buildingCategoryRps = mapper.map(buildingCategories);
        Map<String, Object> result = new HashMap<>();
        result.put("building_category", buildingCategoryRps);
        return Response.ok(result);
    }

    @Override
    public ResponseEntity<SystemResponse<Object>> addOne(HttpServletRequest request, BuildingCategoryRq buildingCategoryRq) {

        boolean exist = buildingCategoryRepository.existsByName(buildingCategoryRq.getName());
        if (exist) {
            return Response.badRequest(StringResponse.NAME_IS_EXIST);
        }
        if (buildingCategoryRq.getBuildingCategory() != null) {
            boolean existsId = buildingCategoryRepository.existsById(buildingCategoryRq.getBuildingCategory());
            if (!existsId) {
                return Response.badRequest(StringResponse.ID_IS_FAKE);
            }
        }

        BuildingCategory buildingCategory = new BuildingCategory();
        mapper.map(buildingCategory, buildingCategoryRq);

        User user = jwtUser.getUser(request);
        if (!Objects.isNull(user)) {
            buildingCategory.setCreatedBy(user.getId());
        }
        buildingCategory.setStatus(Global.ACTIVE);
        buildingCategoryRepository.save(buildingCategory);

        BuildingCategoryRp buildingCategoryRp = mapper.map(buildingCategory);
        return Response.ok(buildingCategoryRp);
    }

    @Override
    public ResponseEntity<SystemResponse<Object>> changeStatus(HttpServletRequest request, StatusRq statusRq) {
        BuildingCategory buildingCategory = buildingCategoryRepository.findById(statusRq.getId()).orElse(null);
        if (Objects.isNull(buildingCategory)) {
            return Response.badRequest(StringResponse.ID_IS_FAKE);
        }
        User user = jwtUser.getUser(request);
        if (!Objects.isNull(user)) {
            buildingCategory.setUpdatedBy(user.getId());
        }
        if (statusRq.getStatus() != Global.NOACTIVE && statusRq.getStatus() != Global.ACTIVE) {
            return Response.badRequest(StringResponse.STATUS_IS_FAKE);
        }
        if (statusRq.getStatus() == Global.ACTIVE) {
            buildingCategory.setStatus(Global.ACTIVE);
        }
        if (statusRq.getStatus() == Global.NOACTIVE) {
            buildingCategory.setStatus(Global.NOACTIVE);
        }
        BuildingCategoryRp buildingCategoryRp = mapper.map(buildingCategory);
        buildingCategoryRepository.save(buildingCategory);
        return Response.ok(buildingCategoryRp);
    }

}
