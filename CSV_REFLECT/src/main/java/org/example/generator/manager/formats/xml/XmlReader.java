package org.example.generator.manager.formats.xml;

import org.example.generator.data.ClassFields;
import org.example.generator.helpers.Converter;
import org.example.generator.manager.interfaces.Reader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class XmlReader implements Reader {
    @Override
    public List read(Class clazz, String sourceLoc, ClassFields classFields)
            throws IOException, SAXException, ParserConfigurationException, InvocationTargetException, InstantiationException, IllegalAccessException {

        File file = new File(sourceLoc + ".xml");
        InputStream inputStream = new FileInputStream(file);
        Converter converter = new Converter();

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = docFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputStream);
        Element element = doc.getDocumentElement();

        Constructor[] constructor = clazz.getConstructors();

        NodeList nodeList = element.getElementsByTagName(clazz.getSimpleName());
        int xd = nodeList.getLength();
        List newObjects = new ArrayList();

        for (int i = 0;i<= nodeList.getLength()-1;i++){
            if (nodeList.getLength() > 0) {
                int finalI = i;
                List<Object> objects = new ArrayList<>();
                classFields.getListOfClassFields().forEach(field -> {
                    Element elementAttribute = (Element) nodeList.item(finalI);
                    String ranking = elementAttribute.getAttribute(field.getName());
                    if (!"".equals(ranking)) {
                        objects.add( converter.stringToType(ranking,field.getType()));
                        //System.out.println(objects);
                    }
                });
                newObjects.add(constructor[0].newInstance(objects.toArray()));
            }
        }

        return newObjects;
    }
}
