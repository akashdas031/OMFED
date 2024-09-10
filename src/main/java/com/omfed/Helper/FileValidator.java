package com.omfed.Helper;

import org.springframework.web.multipart.MultipartFile;

public class FileValidator {

public static boolean validate(MultipartFile file) {
	String type="";
	String filename = file.getOriginalFilename();
	int dot = filename.lastIndexOf('.');
	if(dot>0 && dot<filename.length()-1) {
		 type= filename.substring(dot+1);
		 if(type == null ||
					!type.equals("png") && !type.equals("jpg") 
					&& !type.equals("jpeg")
					) {
         return false;			}
	}
		return true;
	
}
}
