package com.haui.demo.services.mappers;

import com.haui.demo.models.entities.Building;
import com.haui.demo.models.entities.Image;
import com.haui.demo.models.requests.ImageRq;
import com.haui.demo.models.responses.ImageRp;
import com.haui.demo.models.responses.ImageUploadRp;
import com.haui.demo.repositories.BuildingRepository;
import com.haui.demo.repositories.NewsRepository;
import com.haui.demo.utils.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImageMapper {

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private BuildingRepository buildingRepository;
    public List<Image> mapToImage(List<ImageRq> imageRqs, String id, String category) {
        return imageRqs.stream().map(imageRq -> {
            Image image = new Image();
            if(category.equals(Global.BUILDINGS)){
                image.setBuilding(id);
            }
            if(category.equals(Global.NEWS)){
                image.setNews(id);
            }
            image.setName(imageRq.getName());
            image.setType(imageRq.getType());
            return image;
        }).collect(Collectors.toList());
    }

    public List<ImageRp> mapToImageRp(List<Image> images) {
        return images.stream().map(image -> {
            return mapToImageRp(image);
        }).collect(Collectors.toList());
    }

    public ImageRp mapToImageRp(Image image) {
        ImageRp imageRp = new ImageRp();
        imageRp.setName(image.getName());
        imageRp.setType(image.getType());
        imageRp.setUrl(Global.DOMAIN_PATH_FILE_IMAGE + image.getName());
        return imageRp;
    }
    public ImageUploadRp mapToImageUploadRp(Image image) {
        ImageUploadRp imageUploadRp = new ImageUploadRp();
        imageUploadRp.setName(image.getName());
        imageUploadRp.setType(image.getType());
        return imageUploadRp;
    }


}
