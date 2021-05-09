package int221.project.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import int221.project.files.FileInfo;
import int221.project.services.FileStoreServices;

@Controller
@CrossOrigin(origins = "http://52.148.79.33:8080")
public class FilesController {

	@Autowired
	FileStoreServices storeService;

	//////////////////
	// Get All File //
	//////////////////
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

	////////////////////
	// Get Image File //
	////////////////////
	@GetMapping(value = "/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = storeService.load(filename); // Get Resource File
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(file); // Return Resource as IMAGE File
	}

}
