package org.example.generator.manager.formats.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.example.annotations.DateFormat;
import org.example.annotations.DontGenerate;
import org.example.annotations.IgnoreInnerLists;
import org.example.annotations.NullsEquals;
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

    private GeneratorData excelData;
    private ClassFields excelClassFields;

    @Override
    public void generate(String targetLocation, ClassFields classFields, GeneratorData generatorData) throws IOException {
        excelData = generatorData;
        excelClassFields = classFields;
        final String FILETYPE = ".xlsx";

        List<HSSFRow> rows = new ArrayList<>();

        try (HSSFWorkbook wb = new HSSFWorkbook();
             OutputStream fileOut = new FileOutputStream(targetLocation + FILETYPE)) {
            HSSFSheet sheet = wb.createSheet("Sheet 1");
            writeColumnHeaders(sheet, rows);
            writeRowValues(sheet, rows);
            wb.write(fileOut);
        }
    }

    private void writeColumnHeaders(HSSFSheet sheet, List<HSSFRow> rows) {
        int columnIndex = 0;
        rows.add(sheet.createRow(0));
        for (Field field : excelClassFields.getListOfClassFields()) {
            writeColumnHeaderToRow(rows.get(0), field, columnIndex);
            columnIndex++;
        }
    }

    private void writeColumnHeaderToRow(HSSFRow row, Field field, int columnIndex) {
        HSSFCell cell = row.createCell(columnIndex);
        cell.setCellValue(field.getName());
    }

    private void writeRowValues(HSSFSheet sheet, List<HSSFRow> rows) {

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