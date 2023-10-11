package com.shtura.helper.service.locale;

import java.util.Locale;    
import javax.annotation.Resource;    
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

@Configurable
public class MSG {

    private String key;

    @Resource(name = "messageSource")
    private MessageSource messageSource;

    public MSG(String key) {
        super();
        this.key = key;        
    }

    public String value() {
        Locale locale = LocaleContextHolder.getLocale();                        
        return messageSource.getMessage(key, new Object[0], locale);
    }

    @Override
    public String toString() {
        return value();
    }
}
