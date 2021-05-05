package com.haui.demo.services;


import com.haui.demo.models.entities.Building;
import com.haui.demo.models.responses.BuildingDetailRp;
import com.haui.demo.utils.Global;
import com.haui.demo.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExcelService {

    private final Logger logger = LogManager.getLogger(getClass());

    @Value("${app.server.upload.excel}")
    private String urlSaveExcel;

    public <T> String exportExcel(List<String> fields, List<T> buildings, Object typeDataInput) throws IllegalAccessException, IOException {

        Map<String, Field> fieldMap = new HashMap<>();
        setReflectionNFieldMap(fields, fieldMap, typeDataInput);

        //Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Statistical");

        // create Font
        XSSFFont font = getXssfFontHeader(workbook);

        // format for header
        CellStyle headerStyle = getCellStyleHeader(workbook, font);

        Row header1 = sheet.createRow(0);
        createASingleCell(header1, headerStyle, "STT", 0);

        // write Header
        Map<String, String> mapNameExcelGlobal = getMapNameExcel(typeDataInput);
        for (int i = 1; i <= fields.size(); i++)
            createASingleCell(header1, headerStyle, mapNameExcelGlobal.get(fields.get(i - 1)), i);

        // write data
        writeDataBody(fields, buildings, fieldMap, sheet);

        // write file
        return writeFileExcel(workbook);
    }

    private Map<String, String> getMapNameExcel(Object t) {

        return Global.mapNameExcel;
    }

    private CellStyle getCellStyleHeader(XSSFWorkbook workbook, XSSFFont font) {
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setFont(font);
        return headerStyle;
    }

    private XSSFFont getXssfFontHeader(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 10);
        font.setBold(false);
        return font;
    }

    private String writeFileExcel(XSSFWorkbook workbook) throws IOException {
        //Write the workbook in file system
        String nameFile = "Statistical_" + Utils.generateUUID() + "_" + LocalDateTime.now() + ".xlsx";
        String path = urlSaveExcel + nameFile;
        FileOutputStream out = new FileOutputStream(path);
        workbook.write(out);
        out.close();
        logger.info(path, " written successfully on disk.");
        return nameFile;
    }

    private <T> void writeDataBody(List<String> fields, List<T> buildings, Map<String, Field> fieldMap, XSSFSheet sheet)
            throws IllegalAccessException {
        for (int j = 0; j < buildings.size(); j++) {
            Row body = sheet.createRow(j + 1);
            createASingleCell(body, j + 1);
            for (int i = 0; i < fields.size(); i++) {
                Object fieldValue = fieldMap.get(fields.get(i)).get(buildings.get(j));
                createASingleCell(body, fieldValue, i + 1);
            }
        }
    }

    private void setReflectionNFieldMap(List<String> fields, Map<String, Field> fieldMap, Object t) {
        Class<?> aClass = BuildingDetailRp.class;
        fields.forEach(it -> {
            try {
                Field privateNameField = aClass.getDeclaredField(it);
                privateNameField.setAccessible(true);
                fieldMap.put(it, privateNameField);
            } catch (NoSuchFieldException e) {
                logger.error(e);
            }
        });
    }

    private static void createASingleCell(Row row, CellStyle style, String name, int cellNumber) {
        Cell cell = row.createCell(cellNumber);
        cell.setCellValue(name);
        cell.setCellStyle(style);
    }

    private static void createASingleCell(Row row, Object name, int cellNumber) {
        Cell cell = row.createCell(cellNumber);
        if (name != null)
            if (name.getClass().isPrimitive())
                cell.setCellValue((double) name);
            else
                cell.setCellValue(name.toString());

        cell.setCellStyle(null);
    }

    private static void createASingleCell(Row row, int name) {
        Cell cell = row.createCell(0);
        cell.setCellValue(name);
        cell.setCellStyle(null);
    }

}
