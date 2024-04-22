package com.cbc.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileUploadController {

	private static String UPLOAD_FOLDER="D:\\CBC training\\CBC KT SESSON\\fileUpload\\";
	
	@GetMapping("/")
	public String index() {
		return "/upload";
	}
	
	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file")  MultipartFile file,RedirectAttributes redirectAttributes) throws IOException {
		
		if(file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message","Please upload the file with data");
			return "redirect:uploadStatus";
		}
		byte bytes[]=file.getBytes();
		Path path=Paths.get(UPLOAD_FOLDER+file.getOriginalFilename());
		Files.write(path, bytes);
		redirectAttributes.addFlashAttribute("message","file "+ file.getOriginalFilename()+" has been successfully uploaded");
		return "redirect:/uploadStatus";
		
	}
	@GetMapping("/uploadStatus")
	public String uploadStatus() {
		return "uploadStatus";
	}
	
}
