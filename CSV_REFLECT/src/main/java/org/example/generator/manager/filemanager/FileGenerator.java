package org.example.generator.manager.filemanager;


import org.example.generator.data.ClassFields;
import org.example.generator.data.FileType;
import org.example.generator.data.GeneratorData;
import org.example.generator.data.ListSorterUtil;
import org.example.generator.providers.FileGeneratorProvider;
import org.example.generator.manager.interfaces.Generator;

import java.util.List;

public class FileGenerator {

    private final GeneratorData generatorData;
    private final ClassFields classFields;
    private final String targetLocation;

    private static FileGenerator instance;

    public static synchronized FileGenerator getInstance(List list, String targetLocation) {
        if (instance == null) {
            instance = new FileGenerator(list, targetLocation);
        }
        return instance;
    }

    private FileGenerator(List list, String targetLocation) {
        this.classFields = new ClassFields(list.get(0).getClass());
        this.generatorData = new GeneratorData(ListSorterUtil.sortValues(list, classFields));
        this.targetLocation = targetLocation;
    }

    public void generate(FileType type) throws Exception {
        Generator generator = new FileGeneratorProvider().chooseGenerator(type);
        generator.generate(targetLocation, classFields, generatorData);
    }
}