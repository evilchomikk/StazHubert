package org.example.generator.manager.formats.xml;

import org.example.annotations.DateFormat;
import org.example.annotations.IgnoreField;
import org.example.annotations.IgnoreInnerLists;
import org.example.annotations.NullsEquals;
import org.example.generator.data.ClassFields;
import org.example.generator.data.GeneratorData;
import org.example.generator.manager.interfaces.Generator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class XmlGenerator implements Generator {
    @Override
    public void generate(String targetLoc, ClassFields classFields, GeneratorData generatorData)
            throws IOException, ParserConfigurationException, TransformerException {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();


        Element root = doc.createElement("list");
        doc.appendChild(root);

        generatorData.getListOfObjects().forEach(obj -> {
            Element parent = doc.createElement(obj.getClass().getSimpleName());
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
                    //Element element = doc.createElement(field.getName());
                    //parent.appendChild(element);
                    if (field.getType().equals(List.class)) {
                        if (!obj.getClass().isAnnotationPresent(IgnoreInnerLists.class)) {
                            parent.setAttribute(field.getName(), String.valueOf(fieldValue));
                        }
                    }else {
                        parent.setAttribute(field.getName(), String.valueOf(fieldValue));
                    }
                    //element.setAttribute(field.getName(), String.valueOf(fieldValue));
                    // element.set(String.valueOf( fieldValue));
                }
            }
            root.appendChild(parent);
        });
        writeXml(doc, targetLoc);
    }
    private static void writeXml(Document doc,
                                 String targetLock)
            throws TransformerException, IOException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);

        FileWriter writer = new FileWriter(targetLock + ".xml");
        BufferedWriter buffer = new BufferedWriter(writer);
        try {
            transformer.transform(source, new StreamResult(buffer));
        } finally {
            buffer.close();
            writer.close();
        }

    }
}
