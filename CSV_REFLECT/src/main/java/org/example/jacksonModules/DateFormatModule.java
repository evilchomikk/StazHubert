package org.example.jacksonModules;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.example.annotations.DateFormat;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DateFormatModule extends SimpleModule {

    @Override
    public void setupModule(SetupContext context) {
        super.setupModule(context);
        context.addBeanSerializerModifier(new DateFormatSerializerModifier());
    }

    private static class DateFormatSerializerModifier extends BeanSerializerModifier {

        @Override
        public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc,
                                                         List<BeanPropertyWriter> beanProperties) {
            List<BeanPropertyWriter> modifiedProperties = new ArrayList<>();

            for (BeanPropertyWriter writer : beanProperties) {
                AnnotatedMember propertyMember = writer.getMember();
                if (propertyMember.hasAnnotation(DateFormat.class)) {
                    modifiedProperties.add(new DateFormatBeanPropertyWriter(writer));
                } else {
                    modifiedProperties.add(writer);
                }
            }

            return modifiedProperties;
        }
    }

    private static class DateFormatBeanPropertyWriter extends BeanPropertyWriter {

        public DateFormatBeanPropertyWriter(BeanPropertyWriter base) {
            super(base);
        }

        @Override
        public void serializeAsField(Object bean, JsonGenerator gen, SerializerProvider prov) throws Exception {
            if (getMember().getRawType().isAssignableFrom(LocalDate.class)) {
                LocalDate date = (LocalDate) getMember().getValue(bean);
                if (date != null) {
                    DateFormat dateFormatAnnotation = getMember().getAnnotation(DateFormat.class);
                    String pattern = dateFormatAnnotation != null ? dateFormatAnnotation.dateFormat() : "yyyy-MM-dd";
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

                    String formattedDate = dateFormatter.format(date);
                    gen.writeStringField(getName(), formattedDate);
                } else {
                    gen.writeNullField(getName());
                }
            } else {
                super.serializeAsField(bean, gen, prov);
            }
        }
    }
}