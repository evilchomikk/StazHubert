package org.example.generator.manager.formats.yaml;

import org.example.annotations.DateFormat;
import org.example.annotations.IgnoreField;
import org.example.annotations.IgnoreInnerLists;
import org.example.annotations.NullsEquals;
import org.example.generator.data.ClassFields;
import org.example.generator.data.GeneratorData;
import org.example.generator.manager.interfaces.Generator;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YamlGenerator implements Generator {
    @Override
    public void generate(String targetLoc, ClassFields classFields, GeneratorData generatorData) throws Exception {

        FileWriter scan = new FileWriter(targetLoc + ".yaml");
        BufferedWriter writer = new BufferedWriter(scan);
        Map<String,Object> objectMap = new HashMap<>();

        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);
        Yaml yaml = new Yaml(options);

        generatorData.getListOfObjects().forEach(obj -> {
            Map<String, Object> fieldsMap = new HashMap<>();
            for (Field field : classFields.getListOfClassFields()) {
                if (!field.isAnnotationPresent(IgnoreField.class)) {
                    Object fieldValue;
                    field.setAccessible(true);
                    try {
                        fieldValue = field.get(obj);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }


                    if (field.getType().equals(LocalDate.class)) {
                        if (obj.getClass().isAnnotationPresent(DateFormat.class)) {
                            LocalDate date = (LocalDate) fieldValue;
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(obj.getClass().getAnnotation(DateFormat.class).dateFormat());
                            fieldValue = formatter.format(date);
                        }
                    }

                    if (fieldValue == null) {
                        if (obj.getClass().isAnnotationPresent(NullsEquals.class)) {
                            fieldValue = obj.getClass().getAnnotation(NullsEquals.class).nullValue();
                        }
                    }

                    if (field.getType().equals(List.class)) {
                        if (!obj.getClass().isAnnotationPresent(IgnoreInnerLists.class)) {
                            fieldsMap.put(field.getName(), fieldValue);
                        }
                    } else {
                        fieldsMap.put(field.getName(), fieldValue);
                    }
                }
            }
            String pom = yaml.dumpAsMap(fieldsMap);
            objectMap.put(obj.getClass().getSimpleName()+"_"+generatorData.getListOfObjects().indexOf(obj), pom);

        });
            yaml.dump(objectMap, writer);
        writer.close();
        scan.close();
    }
}
