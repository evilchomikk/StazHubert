package org.example.generator.manager.formats.xml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.generator.data.ClassFields;
import org.example.generator.manager.filemanager.objectmappers.ObjectMapperReader;
import org.example.generator.manager.interfaces.Reader;

import java.util.List;

public class XmlReader implements Reader {

    private static final String FILETYPE = ".xml";

    @Override
    public List read(Class clazz, String sourceLocation, ClassFields classFields) throws Exception {
        return new ObjectMapperReader(new XmlMapper()).read(clazz, sourceLocation, FILETYPE);
    }
}