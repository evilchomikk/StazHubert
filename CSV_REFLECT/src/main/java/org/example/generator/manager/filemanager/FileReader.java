package org.example.generator.manager.filemanager;

import org.example.generator.data.ClassFields;
import org.example.generator.data.FileType;
import org.example.generator.providers.FileReaderProvider;
import org.example.generator.manager.interfaces.Reader;

import java.util.List;

public class FileReader {

    ClassFields classFields;
    String sourceLocation;
    Class clazz;
    private static FileReader instance;

    public static synchronized FileReader getInstance(Class clazz, String sourceLocation) {
        if (instance == null) {
            instance = new FileReader(clazz, sourceLocation);
        }
        return instance;
    }

    public FileReader(Class clazz, String sourceLocation) {
        this.classFields = new ClassFields(clazz);
        this.sourceLocation = sourceLocation;
        this.clazz = clazz;
    }

    public List chooseReader(FileType type) throws Exception {
        Reader reader = new FileReaderProvider().chooseReader(type);
        return reader.read(clazz, sourceLocation, classFields);
    }
}