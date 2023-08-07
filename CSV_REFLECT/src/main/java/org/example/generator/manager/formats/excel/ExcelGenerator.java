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

    @Override
    public void generate(String targetLoc, ClassFields classFields, GeneratorData generatorData) throws IOException {
        List<HSSFRow> rows = new ArrayList<>();

            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("Sheet 1");
            OutputStream fileOut = new FileOutputStream(targetLoc + FILETYPE);

            getColTitles(sheet,rows, classFields, generatorData);
            getRowValues(sheet,rows, classFields, generatorData);

            wb.write(fileOut);
            fileOut.close();
            wb.close();

        }

        public void getColTitles(HSSFSheet sheet,List<HSSFRow> rows,ClassFields classFields,GeneratorData data) throws IOException {
            final int[] colIndex = {0};
            rows.add(sheet.createRow(0));
            classFields.getListOfClassFields().forEach(field -> {
                if (!field.isAnnotationPresent(IgnoreField.class)) {
                    if (field.getType().equals(List.class)) {
                        if (!data.getListOfObjects().get(0).getClass().isAnnotationPresent(IgnoreInnerLists.class)) {
                            rows.get(0).createCell(colIndex[0]);
                            rows.get(0).getCell(colIndex[0]).setCellValue(field.getName());
                            colIndex[0]++;
                        }
                    } else {
                        rows.get(0).createCell(colIndex[0]);
                        rows.get(0).getCell(colIndex[0]).setCellValue(field.getName());
                        colIndex[0]++;
                    }
                }
            });


        }

        public void getRowValues(HSSFSheet sheet,List<HSSFRow> rows,ClassFields classFields,GeneratorData data) {


            final int[] rowIndex = {1};
            if (!data.getListOfObjects().get(0).getClass().isAnnotationPresent(DontGenerate.class))
                data.getListOfObjects().forEach(obj -> {

                    final int[] colIndex = {0};

                    rows.add(sheet.createRow(rowIndex[0]));
                    for (Field field : classFields.getListOfClassFields()) {
                        if (!field.isAnnotationPresent(IgnoreField.class)) {
                            Object fieldValue;
                            field.setAccessible(true);
                            try {
                                fieldValue = field.get(obj);
                            } catch (IllegalAccessException e) {
                                throw new RuntimeException(e);
                            }


                            if (field.getType().equals(LocalDate.class)) {
                                if (obj.getClass().isAnnotationPresent(DateFormat.class)) {
                                    LocalDate date = (LocalDate) fieldValue;
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(obj.getClass().getAnnotation(DateFormat.class).dateFormat());
                                    fieldValue = formatter.format(date);
                                }
                            }

                            if (fieldValue == null) {
                                if (obj.getClass().isAnnotationPresent(NullsEquals.class)) {
                                    fieldValue = obj.getClass().getAnnotation(NullsEquals.class).nullValue();
                                }
                            }

                            CellType fieldTypeToCellType = fieldToCellType(field);

                            if (field.getType().equals(List.class)) {
                                if (!obj.getClass().isAnnotationPresent(IgnoreInnerLists.class)) {
                                    rows.get(rowIndex[0]).createCell(colIndex[0], fieldTypeToCellType);
                                    rows.get(rowIndex[0]).getCell(colIndex[0]).setCellValue(String.valueOf(fieldValue));
                                    colIndex[0]++;
                                }
                            } else {
                                rows.get(rowIndex[0]).createCell(colIndex[0], fieldTypeToCellType);
                                rows.get(rowIndex[0]).getCell(colIndex[0]).setCellValue(String.valueOf(fieldValue));
                                colIndex[0]++;
                            }
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
    }

