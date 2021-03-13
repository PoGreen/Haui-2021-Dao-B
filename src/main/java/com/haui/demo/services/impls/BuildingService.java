package com.haui.demo.services.impls;

import com.haui.demo.models.bos.Panigation;
import com.haui.demo.models.bos.Response;
import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.models.entities.Building;
import com.haui.demo.models.entities.User;
import com.haui.demo.models.entities.Ward;
import com.haui.demo.models.requests.BuildingRq;
import com.haui.demo.models.requests.StatusRq;
import com.haui.demo.models.responses.*;
import com.haui.demo.repositories.BuildingCategoryRepository;
import com.haui.demo.repositories.BuildingRepository;
import com.haui.demo.repositories.ImageRepository;
import com.haui.demo.repositories.WardRepository;
import com.haui.demo.services.IBuildingService;
import com.haui.demo.services.IImageService;
import com.haui.demo.services.JwtUser;
import com.haui.demo.services.mappers.BuildingMapper;
import com.haui.demo.utils.Global;
import com.haui.demo.utils.StringResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class BuildingService implements IBuildingService {

    @Autowired
    private WardRepository wardRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BuildingCategoryRepository buildingCategoryRepository;

    @Autowired
    private BuildingMapper mapper;

    @Autowired
    private JwtUser jwtUser;

    @Autowired
    private IImageService imageService;

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public ResponseEntity<SystemResponse<Object>> getAllByUser(HttpServletRequest request, Panigation panigation) {
        User user = jwtUser.getUser(request);
        if (Objects.isNull(user)) {
            return Response.badRequest(StringResponse.USER_ID_FAKE);
        }
        Pageable pageable = PageRequest.of(panigation.getPage() - 1, panigation.getLimit());
        Page<Building> buildings = buildingRepository.findByCreated_by(user.getId(), pageable);
        Page<BuildingRp> buildingRps = mapper.map(buildings);
        for (BuildingRp buildingRp : buildingRps) {
            ImageRp image = imageService.loadBuildingsAvatarImages(buildingRp.getId());
            buildingRp.setImageRp(image);
        }
        return Response.ok(buildingRps);
    }

    @Override
    public ResponseEntity<SystemResponse<Object>> getAllShow(Panigation panigation) {
        Pageable pageable = PageRequest.of(panigation.getPage() - 1, panigation.getLimit());
        Page<Building> buildings = buildingRepository.findByStatusAndBuildingCategoryStatus(Global.ACTIVE, Global.ACTIVE, pageable);
        Page<BuildingRp> buildingRps = mapper.map(buildings);
        for (BuildingRp buildingRp : buildingRps) {
            ImageRp image = imageService.loadBuildingsAvatarImages(buildingRp.getId());
            buildingRp.setImageRp(image);
        }
        return Response.ok(buildingRps);
    }

    @Override
    @Transactional
    public ResponseEntity<SystemResponse<Object>> addOne(HttpServletRequest request, BuildingRq buildingRq) {

        User user = jwtUser.getUser(request);
        if (Objects.isNull(user)) {
            return Response.badRequest(StringResponse.USER_ID_FAKE);
        }

        Ward ward = wardRepository.findById(buildingRq.getWard()).orElse(null);
        if (Objects.isNull(ward)) {
            return Response.badRequest(StringResponse.WARD_IS_FAKE);
        }

        boolean validate = buildingCategoryRepository.existsByIdAndStatus(buildingRq.getBuildingCategory(), Global.ACTIVE);
        if (!validate) {
            return Response.badRequest(StringResponse.BUILDING_CATEGORY_IS_FAKE);
        }
        Building building = new Building();
        mapper.map(building, buildingRq);
        building.setStatus(Global.WAIT);
        building.setCreated_by(user.getId());

        buildingRepository.save(building);
        List<ImageRp> image = imageService.saveImage(building.getId(), Global.BUILDINGS, buildingRq.getImages());
        BuildingDetailRp buildingDetailRp = mapper.maps(building);
        buildingDetailRp.setImageRps(image);

        return Response.ok(buildingDetailRp);
    }

    @Override
    @Transactional
    public ResponseEntity<SystemResponse<Object>> update(HttpServletRequest request, BuildingRq buildingRq) {

        Building building = buildingRepository.findById(buildingRq.getId()).orElse(null);
        if (Objects.isNull(building)) {
            return Response.badRequest(StringResponse.BUILDING_IS_FAKE);
        }

        User user = jwtUser.getUser(request);

        if (Objects.isNull(user)) {
            return Response.badRequest(StringResponse.USER_ID_FAKE);
        }

        if (!building.getCreated_by().equals(user.getId())) {
            return Response.badRequest(StringResponse.USER_HAS_NOT_IT);
        }

        Ward ward = wardRepository.findById(buildingRq.getWard()).orElse(null);
        if (Objects.isNull(ward)) {
            return Response.badRequest(StringResponse.WARD_IS_FAKE);
        }

        boolean validate = buildingCategoryRepository.existsByIdAndStatus(buildingRq.getBuildingCategory(), Global.ACTIVE);
        if (!validate) {
            return Response.badRequest(StringResponse.BUILDING_CATEGORY_IS_FAKE);
        }
        mapper.map(building, buildingRq);
        building.setStatus(Global.WAIT);
        building.setUpdated_by(user.getId());

        buildingRepository.save(building);
        imageRepository.deleteByBuilding(building.getId());
        List<ImageRp> image = imageService.saveImage(building.getId(), Global.BUILDINGS, buildingRq.getImages());
        BuildingDetailRp buildingDetailRp = mapper.maps(building);
        buildingDetailRp.setImageRps(image);

        return Response.ok(buildingDetailRp);
    }

    @Override
    public ResponseEntity<SystemResponse<Object>> changeStatus(HttpServletRequest request, StatusRq statusRq) {
        Building building = buildingRepository.findById(statusRq.getId()).orElse(null);
        if (Objects.isNull(building)) {
            return Response.badRequest(StringResponse.BUILDING_IS_FAKE);
        }
        if (statusRq.getStatus().equals(Global.ACTIVE)) {
            building.setStatus(Global.ACTIVE);
        }

        if (statusRq.getStatus().equals(Global.NOACTIVE)) {
            building.setStatus(Global.NOACTIVE);
        }

        if (statusRq.getStatus().equals(Global.WAIT)) {
            building.setStatus(Global.WAIT);
        }
        buildingRepository.save(building);
        return Response.ok(StringResponse.SUCCESS);
    }

    @Override
    public ResponseEntity<SystemResponse<Object>> getOne(HttpServletRequest request, String id) {
        Building building = buildingRepository.findById(id).orElse(null);
        if (Objects.isNull(building)) {
            return Response.badRequest(StringResponse.BUILDING_IS_FAKE);
        }

        BuildingDetailRp buildingDetailRp = mapper.maps(building);
        List<ImageRp> imageRps = imageService.loadBuildingImages(id);
        buildingDetailRp.setImageRps(imageRps);
        return Response.ok(buildingDetailRp);
    }

}
