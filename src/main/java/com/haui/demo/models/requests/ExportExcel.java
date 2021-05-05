package com.haui.demo.models.requests;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ExportExcel extends BuildingFilter {
    private String fields;

    public ExportExcel() {
        // init ExportExcel
    }

    public List<String> getFields() {
        List<String> lstField = Arrays.asList(fields.split(","));
        lstField.remove(null);
        return lstField.stream()
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public void setFields(String fields) {
        this.fields = fields;
    }
}
