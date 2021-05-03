package int221.project.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import int221.project.files.FileInfo;
import int221.project.files.ResponseMessage;
import int221.project.services.FileStoreServices;

@Controller
@CrossOrigin("http://localhost:8081")
public class FilesController {
	
	@Autowired FileStoreServices storeService;
	
	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";
	    try {
	      String filename = storeService.save(file);
	      message = "Uploaded the file successfully: " + filename;
	      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	    } catch (Exception e) {
	      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	    }
	  }

	@GetMapping("/files")
	  public ResponseEntity<List<FileInfo>> getListFiles() {
	    List<FileInfo> fileInfos = storeService.loadAll().map(path -> {
	      String filename = path.getFileName().toString();
	      String url = MvcUriComponentsBuilder
	          .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

	      return new FileInfo(filename, url);
	    }).collect(Collectors.toList());

	    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
	  }

//	@GetMapping(value = "/files/{filename:.+}", produces = MediaType.IMAGE_JPEG_VALUE)
	@GetMapping(value = "/files/{filename:.+}")
	  @ResponseBody
	  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
//		public Resource getFile(@PathVariable String filename) {
	    Resource file = storeService.load(filename);
	    return ResponseEntity.ok()
		        .contentType(MediaType.IMAGE_JPEG).body(file);
//	    return file;
//	    return ResponseEntity.ok()
//	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	  }
	
}
