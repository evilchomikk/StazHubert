package org.example.generator.manager.formats.yaml;

import org.example.generator.data.ClassFields;
import org.example.generator.manager.interfaces.Reader;
import org.example.testclasses.Employee;
import org.yaml.snakeyaml.*;

import java.io.FileReader;
import java.util.List;

public class YamlReader implements Reader {
    @Override
    public List read(Class clazz, String sourceLoc, ClassFields classFields) throws Exception {

        Yaml yaml = new Yaml();

        // Step 1: Read the YAML file into a List<Person>
        FileReader fileReader = new FileReader(sourceLoc+".yaml");
        List<Employee> persons = yaml.load(fileReader);
        fileReader.close();

        return  persons;
    }









}

