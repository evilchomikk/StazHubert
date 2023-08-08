package org.example.generator.manager.formats.xml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.generator.data.ClassFields;
import org.example.generator.data.GeneratorData;
import org.example.generator.manager.filemanager.objectmappers.ObjectMapperGenerator;
import org.example.generator.manager.interfaces.Generator;

public class XmlGenerator implements Generator {

    private static final String FILETYPE = ".xml";

    @Override
    public void generate(String targetLocation, ClassFields classFields, GeneratorData generatorData) throws Exception {
        new ObjectMapperGenerator(new XmlMapper()).generate(targetLocation, generatorData, FILETYPE);
    }
}
