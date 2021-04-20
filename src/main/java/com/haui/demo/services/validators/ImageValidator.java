package com.haui.demo.services.validators;

import com.haui.demo.models.bos.Response;
import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.models.requests.ImageRq;
import com.haui.demo.utils.Global;
import com.haui.demo.utils.StringResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Component
public class ImageValidator {
    public ResponseEntity<SystemResponse<Object>> validate (MultipartFile[] files){
        for (MultipartFile file: files) {
            String abc = file.getContentType();
            if(!file.getContentType().startsWith("image/"))
            {
                return Response.badRequest(StringResponse.FILE_IS_NOT_IMAGE);
            }
        }
        return Response.ok(StringResponse.SUCCESS);
    }

    public ResponseEntity<SystemResponse<Object>> validates (List<ImageRq> imageRqs){
        for (ImageRq imageRq : imageRqs) {
            File file = new File(Global.PATH_FILE_IMAGE + imageRq.getName());
            if(!file.exists() || !file.isFile()){
                return Response.badRequest(StringResponse.FILE_IS_NOT_EXISTS);
            }
        }
        return Response.ok(StringResponse.SUCCESS);
    }
}
