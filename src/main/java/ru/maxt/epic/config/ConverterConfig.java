package ru.maxt.epic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.maxt.epic.converter.QuoteDtoToQuoteConverter;


@Configuration
public class ConverterConfig implements WebMvcConfigurer {
    @Autowired
    private QuoteDtoToQuoteConverter qq;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(qq);
    }
}
