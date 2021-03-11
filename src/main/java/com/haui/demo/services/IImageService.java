package com.haui.demo.services;

import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.models.requests.ImageRq;
import com.haui.demo.models.responses.ImageRp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IImageService {
    ResponseEntity<SystemResponse<Object>> loadImage(List<MultipartFile> images) throws IOException;

    List<ImageRp> saveImage(String idBelongs, String category, List<ImageRq> imageRqs);
}
