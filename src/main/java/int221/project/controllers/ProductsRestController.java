package int221.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import int221.project.models.Products;
import int221.project.repositories.ProductsJpaRepository;


@RestController
public class ProductsRestController {
	
	@Autowired
	ProductsJpaRepository repo;
	
    @GetMapping("/products/{code}")
    public Products show(@PathVariable String code) {

        return repo.findById(code).orElse(null);
    }
    
    @GetMapping("/products")
    public List<Products> showAll(){
    	return repo.findAll();
    }
	
}
