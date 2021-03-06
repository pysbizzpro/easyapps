package com.pysbizz.pdfreader.pdfreader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pysbizz.pdfreader.pdfreader.services.PDFReader;
import com.pysbizz.pdfreader.pdfreader.services.WriteVoterDataToExcel;

@Controller
public class PdfReaderController {
	public static String UPLOADED_FOLDER = new File("").getAbsolutePath();
	@GetMapping("/")
	  public String isApplicationUp() {
		return "upload";
	}

	
	 @PostMapping("/upload") // //new annotation since 4.3
	 public ResponseEntity<Resource> singleFileUpload(@RequestParam("file") MultipartFile file,
	                                   RedirectAttributes redirectAttributes,@RequestParam("formats") String formats) {

		
	        if (file.isEmpty()) {
	            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
	           // return "redirect:uploadStatus";
	        }

	        try {

	            // Get the file and save it somewhere
	            byte[] bytes = file.getBytes();
	            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
	            Files.write(path, bytes);

	            redirectAttributes.addFlashAttribute("message",
	                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

	        	PDFReader pdfReader = new PDFReader();
	    		
	        	String fileOutPath = null;
	        	try {
	        		fileOutPath = pdfReader.readPDFData(bytes,formats);
	    		} catch (IOException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}

	        //return "redirect:/uploadStatus";
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Disposition", String.format("inline; filename=\"" + file.getOriginalFilename().replace(".pdf",".xlsx") + "\""));
	        Path outputPath = Paths.get(fileOutPath);
	        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(outputPath));
	 
	        return ResponseEntity.ok()
	                .headers(headers)
	                .contentLength(WriteVoterDataToExcel.outputfile.length())
	                .contentType(MediaType.parseMediaType(Files.probeContentType(outputPath)))
	                .body(resource);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    @GetMapping("/uploadStatus")
	    public String uploadStatus() {
	        return "uploadStatus";
	    }
	
	
}
