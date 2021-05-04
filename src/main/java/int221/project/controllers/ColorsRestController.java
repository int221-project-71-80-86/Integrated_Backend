package int221.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import int221.project.models.Colors;
import int221.project.repositories.ColorsJpaRepositories;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class ColorsRestController {

	@Autowired ColorsJpaRepositories colorRepo;
	
	@GetMapping("/colors")
    public List<Colors> getColors() {
    	return colorRepo.findAll();
    }
}
