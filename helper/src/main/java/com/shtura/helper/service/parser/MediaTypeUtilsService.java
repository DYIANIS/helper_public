package com.shtura.helper.service.parser;

import javax.servlet.ServletContext;

import org.springframework.http.MediaType;

import com.shtura.helper.parser.MediaTypeUtils;

public class MediaTypeUtilsService {
	
	public MediaTypeUtilsService(){
		
	}

    public MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {
        
        return MediaTypeUtils.getMediaTypeForFileName(servletContext, fileName);
    }
}
