package com.haui.demo.services;

import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.models.requests.ImageRq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IImageService {
    ResponseEntity<SystemResponse<Object>> loadImage(List<MultipartFile> images) throws IOException;

    ResponseEntity<SystemResponse<Object>> saveImage(String idBelongs,String category,List<ImageRq> imageRqs);
}
