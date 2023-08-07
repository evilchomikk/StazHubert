package org.example.interfaces;


import org.example.data.ClassFields;
import org.example.data.GeneratorData;

public interface Generator {
    void generate(String targetLoc, GeneratorData generatorData)
            throws Exception;
}
