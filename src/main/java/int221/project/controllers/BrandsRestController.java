package int221.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import int221.project.models.Brands;
import int221.project.repositories.BrandsJpaRepositories;

@RestController
@CrossOrigin(origins = "http://172.23.1.1:8080")
public class BrandsRestController {
	
	@Autowired BrandsJpaRepositories brandRepo;
	
	@GetMapping("/brands")
    public List<Brands> getBrands() {
    	return brandRepo.findAll();
    }
}
