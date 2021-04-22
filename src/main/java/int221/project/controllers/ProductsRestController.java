package int221.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import int221.project.repositories.ProductsJpaRepository;

@RestController
public class ProductsRestController {
	
	@Autowired
	ProductsJpaRepository repo;
	
}
