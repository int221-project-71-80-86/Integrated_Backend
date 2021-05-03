package int221.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import int221.project.models.ProductColorsId;
import int221.project.models.Productcolors;
import int221.project.models.Products;
import int221.project.repositories.ColorsJpaRepositories;
import int221.project.repositories.ProdColorsJpaRepository;
import int221.project.repositories.ProductsJpaRepository;
import int221.project.services.FileStoreServices;

@RestController
@CrossOrigin("http://localhost:8081")
public class TestRestController {
	@Autowired ProductsJpaRepository prodRepo;
	@Autowired ProdColorsJpaRepository pcRepo;
	@Autowired ColorsJpaRepositories colorRepo;
	@Autowired FileStoreServices storeService;
	
	@GetMapping("/pc")
	public List<Productcolors> testMapping2() {
		return pcRepo.findAll();
	}
	
	@GetMapping("/pc/{a}")
	public List<Productcolors> testMapping3(@PathVariable Integer a) {
		return pcRepo.findProductcolorsByProductcode(a);
	}
	
//	@GetMapping("/test")
//	public String test1() {
//		return storeService.generateRandomString();
//	}

}
