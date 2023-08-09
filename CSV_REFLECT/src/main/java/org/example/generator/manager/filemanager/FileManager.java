package org.example.generator.manager.filemanager;

import lombok.NoArgsConstructor;
import org.example.annotations.DontGenerate;
import org.example.generator.data.FileType;

import java.util.List;

@NoArgsConstructor
public class FileManager {

    private static FileManager instance;

    public static FileManager getInstance() {
        if (instance == null) {
            synchronized (FileManager.class) {
                if (instance == null) {
                    instance = new FileManager();
                }
            }
        }
        return instance;
    }

    public void generate(FileType type, List list, String targetLock) throws Exception {
        if (!list.isEmpty() && !list.get(0).getClass().isAnnotationPresent(DontGenerate.class)) {
            FileGenerator.getInstance(list, targetLock).generate(type);
        } else {
            System.out.println("Nie udało sie wygenerowac pliku: Lista jest pusta lub zabroniono generowania pliku dla tej klasy");
        }
    }

    public List read(FileType type, Class clazz, String sourceLocation) throws Exception {
        return FileReader.getInstance(clazz, sourceLocation).chooseReader(type);
    }
}