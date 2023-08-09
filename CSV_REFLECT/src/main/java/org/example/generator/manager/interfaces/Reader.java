package org.example.generator.manager.interfaces;

import org.example.generator.data.ClassFields;

import java.util.List;

public interface Reader {

    List read(Class clazz, String sourceLoc, ClassFields classFields)
            throws Exception;
}