package org.example.interfaces;


import org.example.data.ClassFields;

import java.util.List;

public interface Reader {

    List read(Class clazz, String sourceLoc, ClassFields classFields)
            throws Exception;

}
