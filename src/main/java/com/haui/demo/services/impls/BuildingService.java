package com.haui.demo.services.impls;

import com.haui.demo.models.bos.Panigation;
import com.haui.demo.models.bos.Response;
import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.models.entities.Building;
import com.haui.demo.models.entities.User;
import com.haui.demo.models.entities.Ward;
import com.haui.demo.models.requests.BuildingFilter;
import com.haui.demo.models.requests.BuildingRq;
import com.haui.demo.models.requests.ExportExcel;
import com.haui.demo.models.requests.StatusRq;
import com.haui.demo.models.responses.*;
import com.haui.demo.repositories.BuildingCategoryRepository;
import com.haui.demo.repositories.BuildingRepository;
import com.haui.demo.repositories.ImageRepository;
import com.haui.demo.repositories.WardRepository;
import com.haui.demo.services.*;
import com.haui.demo.services.mappers.BuildingMapper;
import com.haui.demo.services.validators.BuildingValidator;
import com.haui.demo.utils.Global;
import com.haui.demo.utils.StringResponse;
import com.haui.demo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
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

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PagingService pagingService;

    @Autowired
    private BuildingValidator validator;

    @Autowired
    private ExcelService excelService;

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
    public ResponseEntity<SystemResponse<Object>> filters(HttpServletRequest request, BuildingFilter filter) {
        Paging<List<Building>> paging = this.filter(filter);

        List<BuildingRp> buildingRps = mapper.map(paging.getData());

        Paging<List<BuildingRp>> pagingDTO = pagingService.mapPagingDTO(buildingRps, paging);

        return Response.ok(pagingDTO);
    }

    @Override
    public ResponseEntity<SystemResponse<Object>> userFilters(HttpServletRequest request, BuildingFilter filter) {
        User user = jwtUser.getUser(request);
        filter.setUser(user.getId());
        Paging<List<Building>> paging = this.filter(filter);

        List<BuildingRp> buildingRps = mapper.map(paging.getData());

        Paging<List<BuildingRp>> pagingDTO = pagingService.mapPagingDTO(buildingRps, paging);

        return Response.ok(pagingDTO);
    }

    @Override
    public ResponseEntity<SystemResponse<String>> exportExcel(ExportExcel exportExcel) throws IOException, IllegalAccessException {
        ResponseEntity<SystemResponse<String>> validate = validator.validate(exportExcel);
        if (!validate.getStatusCode().is2xxSuccessful()) return validate;

        List<Building> buildings = this.filterNoPaging(exportExcel);

        List<BuildingDetailRp> buildingDetailRps = mapper.mapToBuildingDetailRps(buildings);
        String fileName = excelService.exportExcel(exportExcel.getFields(), buildingDetailRps, new BuildingDetailRp());
        String urlStatistical = "tranducdao" + fileName;
        return Response.ok(urlStatistical);
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
        building.setCreated_by(user.getId());
        building.setStatus(Global.WAIT);
        building.setUser(user.getId());

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

    public Paging<List<Building>> filter(BuildingFilter filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Building> cq = cb.createQuery(Building.class);
        CriteriaQuery<Long> cq1 = cb.createQuery(Long.class);
        Root<Building> root = cq.from(Building.class);

        cq.select(root);
        cq1.select(cb.count(cq1.from(Building.class)));

        List<Predicate> predicates = buildPredicates(filter, cb, root);

        return pagingService.getListPaging(filter, cb, cq, cq1, root, predicates);
    }

    public List<Building> filterNoPaging(BuildingFilter filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Building> cq = cb.createQuery(Building.class);
        CriteriaQuery<Long> cq1 = cb.createQuery(Long.class);
        Root<Building> root = cq.from(Building.class);

        cq.select(root);
        cq1.select(cb.count(cq1.from(Building.class)));

        List<Predicate> predicates = buildPredicates(filter, cb, root);

        cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(root.get("createdAt")));
        List<Building> building = entityManager.createQuery(cq)
                .getResultList();
        return building;
    }

    private List<Predicate> buildPredicates(BuildingFilter filter, CriteriaBuilder cb, Root<Building> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getWard() != null)
            predicates.add(cb.equal(root.get("ward"), filter.getWard()));
        if (filter.getFloorArea() != null)
            predicates.add(cb.equal(root.get("floorArea"), filter.getFloorArea()));
        if (filter.getBedRoom() != null)
            predicates.add(cb.equal(root.get("bedroom"), filter.getBedRoom()));
        if (filter.getFunctionRoom() != null)
            predicates.add(cb.equal(root.get("functionRoom"), filter.getFunctionRoom()));
        if (filter.getPrice() != null)
            predicates.add(cb.greaterThanOrEqualTo(root.get("price"), filter.getPrice()));
        if (filter.getStatus() != null)
            predicates.add(cb.equal(root.get("status"), filter.getStatus()));
        if (filter.getSaleRent() != null)
            predicates.add(cb.equal(root.get("saleRent"), filter.getSaleRent()));
        if (Utils.notNullAndEmpty(filter.getDirection()))
            predicates.add(cb.equal(root.get("direction"), filter.getDirection().trim()));
        if (Utils.notNullAndEmpty(filter.getBuildingCategory()))
            predicates.add(cb.equal(root.get("buildingCategory"), filter.getBuildingCategory().trim()));
        if (Utils.notNullAndEmpty(filter.getUser()))
            predicates.add(cb.equal(root.get("user"), filter.getUser().trim()));
        return predicates;
    }
}
