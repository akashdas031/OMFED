package com.omfed.Configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "image")
@Getter
@Setter
public class ImageProperties {
   
	private String uploadDir;
	
}
