package org.example.generator.manager.filemanager;

import lombok.NoArgsConstructor;
import org.example.annotations.DontGenerate;
import org.example.generator.data.FileType;

import java.util.List;

@NoArgsConstructor
public class FileManager {

    public void generate(FileType type, List list, String targetLock) throws Exception {
        if (!list.isEmpty()) {
            if (!list.get(0).getClass().isAnnotationPresent(DontGenerate.class))
                new FileGenerator().chooseGenerator(type, list, targetLock);
        } else {
            System.out.println("Nie udało sie wygenerowac pliku: Lista jest pusta");
        }
    }

    public List read(FileType type, Class clazz, String sourceLock) throws Exception {
        return new FileReader().chooseReader(type, clazz, sourceLock);
    }

}
