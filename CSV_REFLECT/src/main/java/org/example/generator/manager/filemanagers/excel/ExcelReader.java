package org.example.generator.manager.filemanagers.excel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.example.generator.data.ClassFields;
import org.example.generator.helpers.Converter;
import org.example.generator.manager.interfaces.Reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader implements Reader {
    private final String FILETYPE = ".xlsx";
    @Override
    public List read(Class clazz, String sourceLoc, ClassFields classFields) throws IOException, InvocationTargetException, InstantiationException, IllegalAccessException {
        FileInputStream file = new FileInputStream(new File(sourceLoc + FILETYPE));
        Constructor<?>[] constructor = clazz.getConstructors();
        List<Object> newObjectsList = new ArrayList<>();

        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet = workbook.getSheetAt(0);
        Converter converter = new Converter();
        int numberOfFields = classFields.getListOfClassFields().size();


        final boolean[] skipFirstIteration = {false};
        sheet.forEach(row -> {
            if (skipFirstIteration[0]) {

                final int[] i = {0};
                Object[] objects = new Object[numberOfFields];
                row.forEach(cell -> {
                    objects[i[0]] = converter.stringToType(cell.getStringCellValue(),classFields.getListOfClassFields().get(i[0]).getType());
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
