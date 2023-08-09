package org.example.generator.manager.formats.json;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.example.generator.data.ClassFields;
import org.example.generator.manager.filemanager.objectmappers.ObjectMapperReader;
import org.example.generator.manager.interfaces.Reader;

import java.util.List;

public class JsonReader implements Reader {

    private static final String FILETYPE = ".json";

    @Override
    public List read(Class clazz, String soureLocation, ClassFields classFields) throws Exception {
        return new ObjectMapperReader(new JsonMapper()).read(clazz, soureLocation, FILETYPE);
    }
}