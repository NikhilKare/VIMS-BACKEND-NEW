package com.app.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;


public class ImageHandlerImpl  {
	
	
	public static String storeImage(String path,MultipartFile imageFile) throws IOException {
		//get complete path
		String completePath=path+File.separator+imageFile.getOriginalFilename();
		System.out.println("Complete Path"+completePath);
		System.out.println("Copied no of Bytes:-"+Files.copy(imageFile.getInputStream(), Paths.get(completePath), StandardCopyOption.REPLACE_EXISTING));
		//save Complete path to the Image in the DB
		return completePath;
	}
	
	public static byte[] restoreImage(String path) throws IOException {
		System.out.println("Image path:-"+path);
		return Files.readAllBytes(Paths.get(path));
	}
	
}
