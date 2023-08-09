package org.example.generator.manager.formats.excel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.example.generator.data.ClassFields;
import org.example.generator.helpers.FileFieldConverter;
import org.example.generator.manager.interfaces.Reader;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader implements Reader {

    @Override
    public List read(Class clazz, String sourceLoc, ClassFields classFields) throws IOException {
        final String FILETYPE = ".xlsx";

        FileInputStream file = new FileInputStream(sourceLoc + FILETYPE);
        Constructor<?>[] constructor = clazz.getConstructors();
        List<Object> newObjectsList = new ArrayList<>();

        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet = workbook.getSheetAt(0);
        FileFieldConverter converter = new FileFieldConverter();
        int numberOfFields = classFields.getListOfClassFields().size();

        final boolean[] skipFirstIteration = {false};
        sheet.forEach(row -> {
            if (skipFirstIteration[0]) {

                final int[] i = {0};
                Object[] objects = new Object[numberOfFields];
                row.forEach(cell -> {
                    objects[i[0]] = converter.stringToType(cell.getStringCellValue(), classFields.getListOfClassFields().get(i[0]).getType());
                    i[0]++;
                });

                try {
                    newObjectsList.add(constructor[0].newInstance(objects));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            skipFirstIteration[0] = true;
        });
        return newObjectsList;
    }
}