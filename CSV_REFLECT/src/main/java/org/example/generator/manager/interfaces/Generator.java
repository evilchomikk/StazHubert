package org.example.generator.manager.interfaces;

import org.example.generator.data.ClassFields;
import org.example.generator.data.GeneratorData;

public interface Generator {
    void generate(String targetLoc, ClassFields classFields, GeneratorData generatorData)
            throws Exception;
}
