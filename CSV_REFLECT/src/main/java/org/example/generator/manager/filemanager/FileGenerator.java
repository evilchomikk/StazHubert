package org.example.generator.manager.filemanager;


import org.example.generator.data.ClassFields;
import org.example.generator.data.FileType;
import org.example.generator.data.GeneratorData;
import org.example.generator.data.ListSorter;
import org.example.generator.providers.FileGeneratorProvider;
import org.example.generator.manager.interfaces.Generator;

import java.util.List;

public class FileGenerator {

    private GeneratorData generatorData;
    private ClassFields classFields;
    private String targetLocation;

    private static FileGenerator instance;

    public static synchronized FileGenerator getInstance(List list, String targetLocation) {
        if (instance == null) {
            instance = new FileGenerator(list, targetLocation);
        }
        return instance;
    }

    public FileGenerator(List list, String targetLocation) {
        this.classFields = new ClassFields(list.get(0).getClass());
        ListSorter listSorter = new ListSorter(list, classFields);
        this.generatorData = new GeneratorData(listSorter.sortValues());
        this.targetLocation = targetLocation;
    }

    public void generate(FileType type) throws Exception {
        Generator generator = new FileGeneratorProvider().chooseGenerator(type);
        generator.generate(targetLocation, classFields, generatorData);
    }
}