package org.example.generator.manager.filemanagers.yaml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.example.generator.data.ClassFields;
import org.example.generator.helpers.Converter;
import org.example.generator.manager.interfaces.Reader;
import org.example.testclasses.Employee;
import org.yaml.snakeyaml.*;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.introspector.PropertyUtils;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Represent;
import org.yaml.snakeyaml.representer.Representer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

