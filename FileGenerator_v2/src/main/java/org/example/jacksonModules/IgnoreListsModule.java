package org.example.jacksonModules;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import org.example.annotations.Column;
import org.example.annotations.IgnoreLists;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IgnoreListsModule extends SimpleModule {
    @Override
    public void setupModule(Module.SetupContext context) {
        super.setupModule(context);
        context.addBeanSerializerModifier(new IgnoreListsSerializerModifier());
    }

    private static class IgnoreListsSerializerModifier extends BeanSerializerModifier {

        @Override
        public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc,
                                                         List<BeanPropertyWriter> beanProperties) {
            List<BeanPropertyWriter> filteredProperties = new ArrayList<>();

            for (BeanPropertyWriter writer : beanProperties) {
                AnnotatedMember propertyMember = writer.getMember();
                if (propertyMember.hasAnnotation(IgnoreLists.class)) {
                    // Check if the property type is List or its subtypes
                    if (!List.class.isAssignableFrom(propertyMember.getRawType())) {
                        filteredProperties.add(writer);
                    }
                } else {
                    filteredProperties.add(writer);
                }
            }

            return filteredProperties;
        }
    }


}
