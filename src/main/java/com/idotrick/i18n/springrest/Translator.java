package com.idotrick.i18n.springrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class Translator {
    private static ResourceBundleMessageSource messageSource;

    @Autowired
    Translator(ResourceBundleMessageSource resourceBundleMessageSource){
        this.messageSource = resourceBundleMessageSource;
    }

    public static String translate(String messageCode){
        return messageSource.getMessage(messageCode, null, LocaleContextHolder.getLocale());
    }
}
