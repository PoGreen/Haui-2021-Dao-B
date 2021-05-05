package com.haui.demo.services.validators;

import com.haui.demo.models.bos.Response;
import com.haui.demo.models.bos.SystemResponse;
import com.haui.demo.models.requests.ExportExcel;
import com.haui.demo.utils.Global;
import com.haui.demo.utils.StringResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuildingValidator {
    /**
     *
     * @param exportExcel
     * @return
     */
    public ResponseEntity<SystemResponse<String>> validate(ExportExcel exportExcel) {
        // validate fields
        List<String> fields = exportExcel.getFields();
        for (String field : fields)
            if (!Global.fieldValidBuilding.contains(field))
                return Response.badRequest(190, StringResponse.FIELD_IN_VALID);

        return Response.ok();
    }
}
