package org.example.jacksonModules;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import org.example.annotations.DontGenerate;

import java.util.ArrayList;
import java.util.List;

public class DontGenerateModule extends SimpleModule {
    @Override
    public void setupModule(SetupContext context) {
        super.setupModule(context);
        context.addBeanSerializerModifier(new DontGenerateSerializerModifier());
    }

    private static class DontGenerateSerializerModifier extends BeanSerializerModifier {

        @Override
        public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc,
                                                         List<BeanPropertyWriter> beanProperties) {
            List<BeanPropertyWriter> filteredProperties = new ArrayList<>();

            for (BeanPropertyWriter writer : beanProperties) {
                AnnotatedMember propertyMember = writer.getMember();
                if (!propertyMember.hasAnnotation(DontGenerate.class)) {
                    filteredProperties.add(writer);
                }
            }
            return filteredProperties;
        }
    }
}