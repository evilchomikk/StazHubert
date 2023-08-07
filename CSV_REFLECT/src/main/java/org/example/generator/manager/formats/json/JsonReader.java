package org.example.generator.manager.formats.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.generator.data.ClassFields;
import org.example.generator.manager.interfaces.Reader;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.util.List;

public class JsonReader implements Reader {
    @Override
    public List read(Class clazz, String sourceLoc, ClassFields classFields) throws IOException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        ObjectMapper mapper = new ObjectMapper();

        Object ogjd = mapper.readValue(Paths.get(sourceLoc).toFile(), Class.forName(clazz.getCanonicalName()));
        System.out.println(ogjd);

        return null;
    }
}
