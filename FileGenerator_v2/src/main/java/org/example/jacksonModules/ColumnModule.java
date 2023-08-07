package org.example.jacksonModules;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import org.example.annotations.Column;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ColumnModule extends SimpleModule {
    @Override
    public void setupModule(Module.SetupContext context) {
        super.setupModule(context);
        context.addBeanSerializerModifier(new ColumnSerializerModifier());
    }

    private static class ColumnSerializerModifier extends BeanSerializerModifier {

        @Override
        public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc,
                                                         List<BeanPropertyWriter> beanProperties) {
            List<BeanPropertyWriter> filteredProperties = new ArrayList<>();
            List<BeanPropertyWriter> annotatedProperties = new ArrayList<>();

            for (BeanPropertyWriter writer : beanProperties) {
                AnnotatedMember propertyMember = writer.getMember();
                if (!propertyMember.hasAnnotation(Column.class)) {
                    filteredProperties.add(writer);
                } else {
                    annotatedProperties.add(writer);
                }
            }

            // Sort the properties with SortBy annotation based on the specified value
            Collections.sort(annotatedProperties, new SortByAnnotationComparator());

            // Add the sorted annotated properties after the non-annotated properties
            filteredProperties.addAll(annotatedProperties);
            return filteredProperties;
        }
    }

    private static class SortByAnnotationComparator implements Comparator<BeanPropertyWriter> {
        @Override
        public int compare(BeanPropertyWriter o1, BeanPropertyWriter o2) {
            AnnotatedMember propertyMember1 = o1.getMember();
            AnnotatedMember propertyMember2 = o2.getMember();
            Column sortByAnnotation1 = propertyMember1.getAnnotation(Column.class);
            Column sortByAnnotation2 = propertyMember2.getAnnotation(Column.class);

            // Compare based on the specified value in the SortBy annotation
            return sortByAnnotation1.value() - sortByAnnotation2.value();
        }
    }
}
