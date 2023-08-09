package org.example.jacksonModules;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import org.example.annotations.NullsEquals;

import java.util.ArrayList;
import java.util.List;

public class NullsEqualsModule extends SimpleModule {

    @Override
    public void setupModule(SetupContext context) {
        super.setupModule(context);
        context.addBeanSerializerModifier(new NullEqualsSerializerModifier());
    }

    private static class NullEqualsSerializerModifier extends BeanSerializerModifier {

        @Override
        public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc,
                                                         List<BeanPropertyWriter> beanProperties) {
            List<BeanPropertyWriter> filteredProperties = new ArrayList<>();

            for (BeanPropertyWriter writer : beanProperties) {
                AnnotatedMember propertyMember = writer.getMember();
                if (!propertyMember.hasAnnotation(NullsEquals.class)) {
                    filteredProperties.add(writer);
                } else {
                    filteredProperties.add(new NullEqualsBeanPropertyWriter(writer));
                }
            }
            return filteredProperties;
        }
    }

    private static class NullEqualsBeanPropertyWriter extends BeanPropertyWriter {

        private final BeanPropertyWriter _writer;

        public NullEqualsBeanPropertyWriter(BeanPropertyWriter writer) {
            super(writer);
            _writer = writer;
        }

        @Override
        public void serializeAsField(Object bean, com.fasterxml.jackson.core.JsonGenerator gen, com.fasterxml.jackson.databind.SerializerProvider prov) throws Exception {
            Object value = _writer.get(bean);
            if (value == null) {
                AnnotatedMember propertyMember = _writer.getMember();
                if (propertyMember.hasAnnotation(NullsEquals.class)) {
                    NullsEquals nullsEqualsAnnotation = propertyMember.getAnnotation(NullsEquals.class);
                    String valueToSet = nullsEqualsAnnotation.nullValue();
                    gen.writeStringField(_writer.getName(), valueToSet);
                } else {
                    super.serializeAsField(bean, gen, prov);
                }
            } else {
                super.serializeAsField(bean, gen, prov);
            }
        }
    }
}
