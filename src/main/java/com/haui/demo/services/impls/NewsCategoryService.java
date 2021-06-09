package com.haui.demo.services.impls;

import com.haui.demo.models.bos.Response;
import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.models.entities.BuildingCategory;
import com.haui.demo.models.entities.NewsCategory;
import com.haui.demo.models.entities.User;
import com.haui.demo.models.requests.NewsCategoryRq;
import com.haui.demo.models.requests.StatusRq;
import com.haui.demo.models.responses.BuildingCategoryRp;
import com.haui.demo.models.responses.NewsCategoryRp;
import com.haui.demo.repositories.NewsCategoryRepository;
import com.haui.demo.services.INewsCategoryService;
import com.haui.demo.services.JwtUser;
import com.haui.demo.services.mappers.NewsCategoryMapper;
import com.haui.demo.utils.Global;
import com.haui.demo.utils.StringResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class NewsCategoryService implements INewsCategoryService {

    @Autowired
    private NewsCategoryRepository newsCategoryRepository;
    @Autowired
    private NewsCategoryMapper mapper;
    @Autowired
    private JwtUser jwt;

    @Override
    public ResponseEntity<SystemResponse<Object>> getAll(Integer status) {
        List<NewsCategory> categories = new ArrayList<>();
        switch (status){
            case 1:
                categories = newsCategoryRepository.findByStatus(Global.ACTIVE);
                break;
            case 0:
                categories = newsCategoryRepository.findByStatus(Global.NOACTIVE);
                break;
            default:
                categories = newsCategoryRepository.findAll();
        }

        List<NewsCategoryRp> categoryRps = mapper.map(categories);
        return Response.ok(categoryRps);
    }

    @Override
    public ResponseEntity<SystemResponse<Object>> addOne(HttpServletRequest request, NewsCategoryRq newsCategoryRq) {

        boolean existsByName = newsCategoryRepository.existsByName(newsCategoryRq.getName());
        if (existsByName) {
            return Response.badRequest(StringResponse.NAME_IS_EXIST);
        }

        if (newsCategoryRq.getNewscategory() != null) {
            boolean exists = newsCategoryRepository.existsById(newsCategoryRq.getNewscategory());
            if (exists) {
                return Response.badRequest(StringResponse.NEWS_CATEGORY_IS_FAKE);
            }
        }

        NewsCategory newsCategory = mapper.map(newsCategoryRq);
        User user = jwt.getUser(request);
        if (!Objects.isNull(user)) {
            newsCategory.setCreatedBy(user.getId());
        }
        newsCategory.setStatus(Global.ACTIVE);
        newsCategoryRepository.save(newsCategory);
        NewsCategoryRp rp = mapper.map(newsCategory);
        return Response.ok(rp);
    }

    @Override
    public ResponseEntity<SystemResponse<Object>> changeStatus(HttpServletRequest request, StatusRq statusRq) {
        NewsCategory newsCategory = newsCategoryRepository.findById(statusRq.getId()).orElse(null);
        if(Objects.isNull(newsCategory)){
            return Response.badRequest(StringResponse.ID_IS_FAKE);
        }
        User user = jwt.getUser(request);
        if(!Objects.isNull(user)){
            newsCategory.setUpdatedBy(user.getId());
        }
        if(statusRq.getStatus() != Global.NOACTIVE && statusRq.getStatus() != Global.ACTIVE){
            return Response.badRequest(StringResponse.STATUS_IS_FAKE);
        }
        if(statusRq.getStatus() == Global.ACTIVE){
            newsCategory.setStatus(Global.ACTIVE);
        }
        if (statusRq.getStatus() == Global.NOACTIVE){
            newsCategory.setStatus(Global.NOACTIVE);
        }
        newsCategoryRepository.save(newsCategory);
        NewsCategoryRp rp = mapper.map(newsCategory);
        return Response.ok(rp);

    }
}
