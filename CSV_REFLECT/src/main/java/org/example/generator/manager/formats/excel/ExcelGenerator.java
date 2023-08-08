package org.example.generator.manager.formats.excel;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.example.annotations.*;
import org.example.generator.data.ClassFields;
import org.example.generator.data.GeneratorData;
import org.example.generator.manager.interfaces.Generator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ExcelGenerator implements Generator {

    private final String FILETYPE = ".xlsx";
    private GeneratorData excelData;
    private ClassFields excelClassFields;

    @Override
    public void generate(String targetLocation, ClassFields classFields, GeneratorData generatorData) throws IOException {
        excelData = generatorData;
        excelClassFields = classFields;
        List<HSSFRow> rows = new ArrayList<>();

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Sheet 1");
        OutputStream fileOut = new FileOutputStream(targetLocation + FILETYPE);

        getColTitles(sheet, rows);
        getRowValues(sheet, rows);

        wb.write(fileOut);
        fileOut.close();
        wb.close();
    }

    public void getColTitles(HSSFSheet sheet, List<HSSFRow> rows) throws IOException {
        final int[] colIndex = {0};
        rows.add(sheet.createRow(0));
        excelClassFields.getListOfClassFields().forEach(field -> {
            if (isWritable(field)) {
                rows.get(0).createCell(colIndex[0]);
                rows.get(0).getCell(colIndex[0]).setCellValue(field.getName());
                colIndex[0]++;
            }
        });
    }

    public void getRowValues(HSSFSheet sheet, List<HSSFRow> rows) {

        final int[] rowIndex = {1};
        excelData.getListOfObjects().forEach(obj -> {

            final int[] colIndex = {0};

            rows.add(sheet.createRow(rowIndex[0]));
            for (Field field : excelClassFields.getListOfClassFields()) {
                if (isWritable(field)) {
                    Object fieldValue;

                    try {
                        fieldValue = getFieldValue(field, obj);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }

                    CellType fieldTypeToCellType = fieldToCellType(field);

                    rows.get(rowIndex[0]).createCell(colIndex[0], fieldTypeToCellType);
                    rows.get(rowIndex[0]).getCell(colIndex[0]).setCellValue(String.valueOf(fieldValue));
                    colIndex[0]++;
                }
            }
            rowIndex[0]++;
        });
    }

    public CellType fieldToCellType(Field field) {
        if (field.getType().equals(String.class)) {
            return CellType.STRING;
        } else if (field.getType().equals(Boolean.class) || field.getType().equals(boolean.class)) {
            return CellType.BOOLEAN;
        } else {
            return CellType.NUMERIC;
        }
    }

    private boolean isWritable(Field field) {
        if (field.isAnnotationPresent(DontGenerate.class))
            return false;
        else if (field.getType().equals(List.class) && excelData.getClass().isAnnotationPresent(IgnoreInnerLists.class))
            return false;
        else
            return true;
    }

    private Object getFieldValue(Field field, Object obj) throws IllegalAccessException {
        field.setAccessible(true);
        Object fieldValue = field.get(obj);

        if (fieldValue == null) {
            fieldValue = field.getAnnotation(NullsEquals.class).nullValue();
        }

        if (field.getClass().isAnnotationPresent(DateFormat.class)) {
            LocalDate date = (LocalDate) fieldValue;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(field.getClass().getAnnotation(DateFormat.class).dateFormat());
            fieldValue = formatter.format(date);
        }
        return fieldValue;
    }
}

